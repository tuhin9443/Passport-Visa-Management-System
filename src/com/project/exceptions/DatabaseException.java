package com.project.exceptions;

public class DatabaseException extends Throwable {

	private String message = "null";
	public DatabaseException() {
		super();

	}

	public DatabaseException(String message) {
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
