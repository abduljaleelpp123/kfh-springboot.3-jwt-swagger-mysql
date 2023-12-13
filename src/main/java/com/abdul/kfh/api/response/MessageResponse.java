package com.abdul.kfh.api.response;

public class MessageResponse {
  private String message;
  private int errorCode;
  
  private Object responsevalue;
  
  
  
  
  
public MessageResponse() {
	super();
	
}
public MessageResponse(String message, int errorCode) {
	super();
	this.message = message;
	this.errorCode = errorCode;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public int getErrorCode() {
	return errorCode;
}
public void setErrorCode(int errorCode) {
	this.errorCode = errorCode;
}
public Object getResponsevalue() {
	return responsevalue;
}
public void setResponsevalue(Object responsevalue) {
	this.responsevalue = responsevalue;
}



 
}
