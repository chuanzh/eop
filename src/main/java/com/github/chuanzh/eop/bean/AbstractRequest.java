package com.github.chuanzh.eop.bean;

import com.github.chuanzh.eop.annotation.DescNotNull;
import com.github.chuanzh.eop.annotation.DescNote;

public abstract class AbstractRequest {
	@DescNote("授权码")
	@DescNotNull
	private String appKey = null;
	@DescNote("签名")
	@DescNotNull
	private String sign = null;
	@DescNote("接口方法名")
	@DescNotNull
	private String method = null;
	@DescNote("请求id,不需要传，作为日志打印使用")
	private String requestId = null;
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	 
}
