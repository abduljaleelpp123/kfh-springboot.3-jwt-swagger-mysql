package com.abdul.kfh.api.exceptions;

public class RequestParameterNotValidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 public RequestParameterNotValidException(String errorMessage) {  
		
		 super(errorMessage);  
	 }  
}
