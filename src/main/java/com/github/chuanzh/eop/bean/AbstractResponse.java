package com.github.chuanzh.eop.bean;

import com.github.chuanzh.eop.annotation.DescNote;
import com.github.chuanzh.util.FuncStatic;

import net.sf.json.JSONObject;

public abstract class AbstractResponse {
	@DescNote("错误编码")
	private String errorCode = "1000";
	@DescNote("错误信息")
	private String errorMessage = null;
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String returnString(){
		return JSONObject.fromObject(this,FuncStatic.JSON_NONULL_CONFIG).toString();
	}
	
}
