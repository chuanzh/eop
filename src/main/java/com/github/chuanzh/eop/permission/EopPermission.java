package com.github.chuanzh.eop.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.chuanzh.eop.bean.ErrorResponse;
import com.github.chuanzh.eop.permission.db.query.AppConfigQuery;
import com.github.chuanzh.eop.permission.db.query.AppLimitForInterfaceQuery;
import com.github.chuanzh.eop.permission.db.query.MethodConfigQuery;
import com.github.chuanzh.eop.permission.db.rowdata.AppConfigRowData;
import com.github.chuanzh.eop.permission.db.rowdata.AppLimitForInterfaceRowData;
import com.github.chuanzh.eop.permission.db.rowdata.MethodConfigRowData;
import com.github.chuanzh.orm.DbBasicService;
import com.github.chuanzh.orm.DbFactory;
import com.github.chuanzh.util.FuncDate;
import com.github.chuanzh.util.FuncStatic;
import com.github.chuanzh.util.PagerTool;
import com.github.chuanzh.util.SignGenerator;

/**
 * 接口权限校验类
 * @author chuan.zhang
 *
 */
public class EopPermission {
	
	private DbBasicService dbService;
	private AppConfigRowData appConfigData;
	
	private boolean appKeyCheck = false;
	private boolean ipCheck = false;
	private boolean methodCheck = false;
	
	public EopPermission (DbBasicService dbService) {
		this.dbService = dbService;
	}
	
	public ErrorResponse check(HttpServletRequest request) throws Exception {
		ErrorResponse err = null;
		if (this.appKeyCheck) {
			// APPKey校验
			err = this.appKeyCheck(request);
		}
		
		if (err == null && this.ipCheck) {
			// IP校验
			err = this.ipCheck(request);
		}
		
		if (err == null && this.methodCheck) {
			// 接口方法限制校验
			err = this.methodCheck(request);
		}
		
		return err;
	}
	
	/**
	 * appKey校验
	 * 1，非空验证
	 * 2，有效验证
	 * 3，签名验证
	 * 4，appKey是否被锁定
	 * 5，appKey有效期验证
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ErrorResponse appKeyCheck(HttpServletRequest request) throws Exception {
		if(FuncStatic.checkIsEmpty( request.getParameter("appKey") )){
			return ErrorResponse.createErrorResponse(ErrorResponse.ERR_NO_APPKEY);
		}
		
		AppConfigQuery query = DbFactory.instance(dbService, AppConfigQuery.class);
		query.ctnAppKey(request.getParameter("appKey"));
		List<AppConfigRowData> list = query.queryRows();
		if(list.size() == 0){
			return ErrorResponse.createErrorResponse(ErrorResponse.ERR_APPKEY_INVAILD);
		}
		
		//签名校验
		Map<String,String[]> map = request.getParameterMap();
		Map<String,String> datas = new HashMap<String, String>();
		for(String o : map.keySet()){
			if(!"sign".equals(o) && !"SIGN".equals(o) ){
				datas.put(o, map.get(o)[0]);
			}
		}
		String appSecret = list.get(0).getAppSecret();
		String sign = SignGenerator.getSign( datas,appSecret ); 
		String inputSign = request.getParameter("sign");
		if(inputSign == null){
			inputSign = request.getParameter("SIGN");
		}
		if(!sign.equals(inputSign)){
			return ErrorResponse.createErrorResponse(ErrorResponse.ERR_SIGN_INVAILD);
		}
		
		appConfigData = list.get(0);
		
		if (appConfigData.getIsLock() == 1) {
			return ErrorResponse.createErrorResponse(ErrorResponse.ERR_APPKEY_INVAILD);
		}
		
		if (!FuncStatic.checkIsEmpty(appConfigData.getValidDate()) && FuncDate.difference(appConfigData.getValidDate(), FuncDate.getNowDate()) > 0) {
			return ErrorResponse.createErrorResponse(ErrorResponse.ERR_APPKEY_EXPIRED);
		}
		
		return null;
	}
	
	
	private String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!FuncStatic.checkIsEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!FuncStatic.checkIsEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
	
	/**
	 * IP校验
	 * 比较请求的IP与appKey绑定的IP是否相同
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ErrorResponse ipCheck(HttpServletRequest request) throws Exception {
		if (!FuncStatic.checkIsEmpty(appConfigData.getBindIp())) {
			if (!getIp(request).equals(appConfigData.getBindIp())) {
				return ErrorResponse.createErrorResponse(ErrorResponse.ERR_IP_INVAILD);
			}
		}
		
		return null;
	}
	
	/**
	 * 接口方法校验
	 * 1，接口方法总次数限制
	 * 2，接口方法每日限制
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private ErrorResponse methodCheck(HttpServletRequest request) throws Exception {
		if (appConfigData.getAppLimitType() == 1) {
			// 对所有接口
			boolean isUpdate = false;
			
			if (appConfigData.getTotalLimitTimes() != -1) {
				if (appConfigData.getTotalVisitsTimes() >= appConfigData.getTotalLimitTimes()) {
					return ErrorResponse.createErrorResponse(ErrorResponse.ERR_EXCEED_LIMITS);
				} else {
					appConfigData.setTotalVisitsTimes(appConfigData.getTotalVisitsTimes()+1);
					isUpdate = true;
				}
			}
			
			if (appConfigData.getDateLimitTimes() != -1) {
				if (appConfigData.getDateVisitsTimes() >= appConfigData.getDateLimitTimes()) {
					return ErrorResponse.createErrorResponse(ErrorResponse.ERR_EXCEED_LIMITS);
				} else {
					appConfigData.setDateVisitsTimes(appConfigData.getDateVisitsTimes()+1);
					isUpdate = true;
				}
			}
			
			//访问频率控制
			
			if (isUpdate) {
				appConfigData.update();
			}
		} else if (appConfigData.getAppLimitType() == 2) {
			// 针对单个接口
			String apiName = request.getParameter("method").toString();
			MethodConfigQuery mcQuery = DbFactory.instance(dbService, MethodConfigQuery.class);
			mcQuery.ctnName(apiName);
			mcQuery.setPagerDto(PagerTool.init(1,1));
			List<MethodConfigRowData> mcDatas = mcQuery.queryRows();
			if (mcDatas.size() > 0) {
				AppLimitForInterfaceQuery query = DbFactory.instance(dbService, AppLimitForInterfaceQuery.class);
				query.ctnAppId(String.valueOf(appConfigData.getId()));
				query.ctnMethodId(String.valueOf(mcDatas.get(0).getId()));
				query.setPagerDto(PagerTool.init(1,1));
				List<AppLimitForInterfaceRowData> list = query.queryRows();
				if (list.size() > 0) {
					AppLimitForInterfaceRowData appLimitForInt = list.get(0);
					boolean isUpdate = false;
					
					if (appLimitForInt.getTotalLimitTimes() != -1) {
						if (appLimitForInt.getTotalVisitsTimes() >= appLimitForInt.getTotalLimitTimes()) {
							return ErrorResponse.createErrorResponse(ErrorResponse.ERR_EXCEED_LIMITS);
						} else {
							appLimitForInt.setTotalVisitsTimes(appLimitForInt.getTotalVisitsTimes()+1);
							isUpdate = true;
						}
					}
					
					if (appLimitForInt.getDateLimitTimes() != -1) {
						if (appLimitForInt.getDateVisitsTimes() >= appLimitForInt.getDateLimitTimes()) {
							return ErrorResponse.createErrorResponse(ErrorResponse.ERR_EXCEED_LIMITS);
						} else {
							appLimitForInt.setDateVisitsTimes(appLimitForInt.getDateVisitsTimes()+1);
							isUpdate = true;
						}
					}
					
					//访问频率控制代码
					
					if (isUpdate) {
						appLimitForInt.update();
					}
					
				} else {
					return ErrorResponse.createErrorResponse(ErrorResponse.ERR_METHOD_FORBID);
				}
				
			} else {
				return ErrorResponse.createErrorResponse(ErrorResponse.ERR_METHOD_INVAILD);
			}
			
		}
		
		return null;
	}

	public boolean isAppKeyCheck() {
		return appKeyCheck;
	}

	public EopPermission setAppKeyCheck(boolean appKeyCheck) {
		this.appKeyCheck = appKeyCheck;
		return this;
	}

	public boolean isIpCheck() {
		return ipCheck;
	}

	public EopPermission setIpCheck(boolean ipCheck) {
		this.ipCheck = ipCheck;
		return this;
	}

	public boolean isMethodCheck() {
		return methodCheck;
	}

	public EopPermission setMethodCheck(boolean methodCheck) {
		this.methodCheck = methodCheck;
		return this;
	}
	 
	
}
