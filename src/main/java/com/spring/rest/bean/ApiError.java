package com.spring.rest.bean;

public class ApiError {

	private String status;
	private String errorMsg;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	@Override
	public String toString() {
		return "ApiError [status=" + status + ", errorMsg=" + errorMsg + "]";
	}
	
	
	
	
}
