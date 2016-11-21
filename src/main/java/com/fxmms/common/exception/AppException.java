package com.fxmms.common.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 2376627903957963613L;

	public AppException(String message) {
		super(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
}