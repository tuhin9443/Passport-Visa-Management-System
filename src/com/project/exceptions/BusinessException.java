package com.project.exceptions;

public class BusinessException  extends Throwable {
	private String message = "null";

	public BusinessException() {
		super();
		
	}

	public BusinessException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
