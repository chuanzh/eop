package com.github.chuanzh.eop;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.github.chuanzh.eop.annotation.IjDbService;
import com.github.chuanzh.eop.annotation.IjHttpServletRequest;
import com.github.chuanzh.eop.annotation.IjHttpServletResponse;
import com.github.chuanzh.eop.bean.AbstractResponse;
import com.github.chuanzh.eop.bean.ErrorResponse;
import com.github.chuanzh.orm.DbBasicService;
import com.github.chuanzh.orm.DbConnectTool;
import com.github.chuanzh.orm.DbFactory;
import com.github.chuanzh.util.FuncFile;
import com.github.chuanzh.util.FuncRandom;
import com.github.chuanzh.util.FuncStatic;

public abstract class  EopFilter implements Filter {
	protected static String ENCODE = null;
	private static Logger logger = Logger.getLogger ( EopFilter.class.getName () );
	protected static HashMap<String, Class> actionList = new HashMap<String, Class>();
	private static int runTimeLimit = 5000;
 	public void init(FilterConfig config) throws ServletException {
		try {
			this.ENCODE = projectEncode();  
			Set<Class<?>> set = FuncFile.getClasses(controlFolder());
			for (Class c : set) {
				String p = c.getName();
				String key = p
						.substring(p.indexOf(".service.") + 9, p.length());
				actionList.put(key.toLowerCase(), c);
			}
			if(this.getRunTimeLimit() != 0){
				runTimeLimit = this.getRunTimeLimit();
			}
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding(ENCODE);
			response.setContentType("text/html; charset=" + ENCODE);
			startRequest((HttpServletRequest)request);
			long start = System.currentTimeMillis();
			FilterThread thread = new FilterThread();
			thread.setFilter(this);
			thread.setHttpServletRequest((HttpServletRequest)request);
			thread.setHttpServletResponse((HttpServletResponse) response);
			Thread runThread = new Thread(thread);
			try {
				runThread.start();
				runThread.join(runTimeLimit);
				if(runThread.isAlive()){
					runThread.interrupt();
				}
			} catch (Exception e) {
				logger.error(FuncStatic.errorTrace(e));
			}
			
			AbstractResponse myresponse = thread.getMyresponse();
			if(myresponse == null){
				myresponse = ErrorResponse.createErrorResponse(ErrorResponse.REQUEST_TIME_OUT );
			}
			this.endRequest(System.currentTimeMillis() - start,(HttpServletRequest)request,myresponse);
			response.getWriter().write(myresponse.returnString());
 		} catch (Exception ioe) {
			logger.error(FuncStatic.errorTrace(ioe));
		}
	}
	
	public ErrorResponse injectParameters (Object controlObject,Object inputRequest, HttpServletRequest request,HttpServletResponse response)  throws Exception {
		if(controlObject != null){
			ErrorResponse err = beforeDoControl(controlObject, inputRequest, request, response);
			if (err != null) {
				return err;
			}

			//设置request的值到inputRequestBean
			ArrayList<Field> paramsList = FuncStatic.findClassAllField(inputRequest.getClass());
			if (request.getAttribute("requestId") != null) {
				BeanUtils.setProperty(inputRequest, "requestId", request.getAttribute("requestId"));
			}else {
				BeanUtils.setProperty(inputRequest, "requestId", FuncRandom.createSequence());
			}
			for(Field f : paramsList){
				if(request.getParameter(f.getName()) != null){
					BeanUtils.setProperty(inputRequest, f.getName(), request.getParameter(f.getName()));
				}
			}
			
			//初始化对象注入
			Field[] file = controlObject.getClass().getDeclaredFields();
			for (int i = 0; i < file.length; i++) {
				Field f = file[i];
				f.setAccessible(true);
				if (f.isAnnotationPresent(IjDbService.class)){
					Class c = f.getAnnotation(IjDbService.class).value();
					Method m = c.getDeclaredMethod("instance", null);
					f.set(controlObject, DbFactory.instanceService((DbConnectTool)m.invoke(c, null),String.valueOf(request.getAttribute("requestId"))));
				}
				
				else if (f.isAnnotationPresent(IjHttpServletResponse.class))
					f.set(controlObject, response);
				else if (f.isAnnotationPresent(IjHttpServletRequest.class)){
					f.set(controlObject, request);
				}
			}
		}
		return null;
	}
	
	public void releaseResource(Object o)  throws Exception {
		if(o != null){
			Field[] file = o.getClass().getDeclaredFields();
			for (int i = 0; i < file.length; i++) {
				Field f = file[i];
				f.setAccessible(true);
				if (f.isAnnotationPresent(IjDbService.class)){
					DbBasicService service = (DbBasicService)f.get(o);
					if(service !=null){
						service.freeResource();
					}
				}
			}
		}
	}
	
	/**
	 * 请求开始
	 * @param request 请求参数
	 */
	public abstract void startRequest(HttpServletRequest request);
	
	/**
	 * 请求结束
	 * @param pageRunTime 执行时长
	 * @param request 请求参数
	 * @param responseBean 返回对象
	 */
	public abstract void endRequest(long pageRunTime, HttpServletRequest request, AbstractResponse responseBean);
	
	/**
	 * 在执行cantorl中的接口方法前的操作，可以是验证权限等
	 * @param controlObject
	 * @param inputRequest
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public abstract ErrorResponse beforeDoControl(Object controlObject,Object inputRequest, HttpServletRequest request,HttpServletResponse response)  throws Exception;
	
	public abstract int getRunTimeLimit();
	public abstract String projectEncode();
	public abstract String controlFolder();
	
 	public void destroy() {
	}
	
	
}
