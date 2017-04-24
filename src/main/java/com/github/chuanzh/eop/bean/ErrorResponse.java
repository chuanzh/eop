package com.github.chuanzh.eop.bean;

import java.util.HashMap;

public class ErrorResponse extends AbstractResponse{
	
	/**
	 * 缺少appKey参数
	 */
	public final static String ERR_NO_APPKEY = "1001";
	
	/**
	 * 无效appKey参数
	 */
	public final static String ERR_APPKEY_INVAILD = "1002";
	
	/**
	 * IP受限
	 */
	public final static String ERR_IP_INVAILD = "1003";
	
	/**
	 * 无权访问指定接口
	 */
	public final static String ERR_METHOD_FORBID = "1004";
	
	
	/**
	 * appKey已过期
	 */
	public final static String ERR_APPKEY_EXPIRED = "1005";
	
	/**
	 * 接口请求次数超限制
	 */
	public final static String ERR_EXCEED_LIMITS = "1006";
	
	/**
	 * 无效的方法名
	 */
	public final static String ERR_METHOD_INVAILD = "1007";
	
	/**
	 * 无效签名
	 */
	public final static String ERR_SIGN_INVAILD = "1008";
	
	
	/**
	 * 缺少接口方法名参数
	 */
	public final static String ERR_NO_METHOD = "1009";
	
	/**
	 * 缺少必选参数
	 */
	public final static String ERR_MISS_PARAM = "1010";
	
	/**
	 * 业务逻辑错误
	 */
	public final static String ERR_INNER_LOGIC = "1011";
	
	/**
	 * 无效的sessionId
	 */
	public final static String ERR_SESSION_INVAILD = "21";
	/**
	 * 服务超时
	 */
	public final static String REQUEST_TIME_OUT = "1012"; 
	
	
	private static HashMap<String, String> errorMap = new HashMap<String, String>();
	static {
		errorMap.put(ERR_NO_APPKEY, "缺少appKey参数");
		errorMap.put(ERR_APPKEY_INVAILD, "无效appKey参数");
		errorMap.put(ERR_IP_INVAILD, "IP受限");
		errorMap.put(ERR_METHOD_FORBID, "无权访问指定接口");
		errorMap.put(ERR_APPKEY_EXPIRED, "appKey已过期");
		errorMap.put(ERR_EXCEED_LIMITS, "接口请求次数超限制");
		errorMap.put(ERR_METHOD_INVAILD, "无效的方法名");
		errorMap.put(ERR_SIGN_INVAILD, "无效签名");
		errorMap.put(ERR_NO_METHOD, "缺少接口方法名参数");
		errorMap.put(ERR_MISS_PARAM, "缺少必选参数");
		
		errorMap.put(ERR_SESSION_INVAILD, "无效的 Session");
		errorMap.put(ERR_INNER_LOGIC, "业务逻辑错误");
		errorMap.put(REQUEST_TIME_OUT, "服务超时");
	}
	public static ErrorResponse createErrorResponse(String errorCode) {
		ErrorResponse er = new ErrorResponse();
		er.setErrorCode(errorCode);
		er.setErrorMessage(errorMap.get(errorCode));
		return er;
	}
	public static ErrorResponse createErrorResponse(String errorCode,String errorMessage) {
		ErrorResponse er = new ErrorResponse();
		er.setErrorCode(errorCode);
		er.setErrorMessage(errorMap.get(errorCode) + "。 " + errorMessage);
		return er;
	}
}
