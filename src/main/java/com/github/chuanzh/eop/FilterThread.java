package com.github.chuanzh.eop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.github.chuanzh.eop.bean.AbstractResponse;
import com.github.chuanzh.eop.bean.ErrorResponse;
import com.github.chuanzh.util.FuncStatic;

public class FilterThread implements Runnable{
	private static Logger logger = Logger.getLogger ( FilterThread.class.getName () );
	private EopFilter filter = null;
	private AbstractResponse myresponse = null;
	private HttpServletRequest request = null;
	private HttpServletResponse response  = null;
	public AbstractResponse getMyresponse() {
		return myresponse;
	}

	public EopFilter getFilter() {
		return filter;
	}

	public void setFilter(EopFilter filter) {
		this.filter = filter;
	}
	
	public void setHttpServletRequest(HttpServletRequest request){
		this.request = request;
	}
	public void setHttpServletResponse(HttpServletResponse response){
		this.response = response;
	}
	@Override
	public void run() {
		myresponse = this.process();
	}
	
	private AbstractResponse process(){
		Object o = null;
		try {
			String className = request.getParameter("method");
			if(className == null){
				return ErrorResponse.createErrorResponse(ErrorResponse.ERR_NO_METHOD);
			}
			try {
				className = className.toLowerCase();
				o = filter.actionList.get(className).newInstance(); 
			} catch (Exception e) {
				return ErrorResponse.createErrorResponse(ErrorResponse.ERR_METHOD_INVAILD);
			}  
			 
			Method m = null;
			Method[] mds = o.getClass().getMethods();
			for(Method item : mds){
				if(item.getName().equals("func")){
					m = item;
					break;
				}
			}
			Class clazz = m.getParameterTypes()[0];
			Object inputRequest = clazz.newInstance();
			ErrorResponse beforeResponse = filter.injectParameters(o,inputRequest,request,response);
			if(beforeResponse != null){
				return beforeResponse;
			}
			AbstractResponse restr = (AbstractResponse) m.invoke(o, inputRequest);
			if (restr != null){
				return restr;
			}
		} catch (Exception e) {
			logger.error(e,e);
		}finally{
			try {
				filter.releaseResource(o);
			} catch (Exception e2) {
				logger.error(FuncStatic.errorTrace(e2));
			}
		}
		return ErrorResponse.createErrorResponse(ErrorResponse.ERR_INNER_LOGIC ,"服务器内部错误");
	}
}
