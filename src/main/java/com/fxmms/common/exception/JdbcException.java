package com.fxmms.common.exception;

public class JdbcException extends AppException {

	private static final long serialVersionUID = -9115422190134712141L;

	public JdbcException(String message) {
		super(message);
	}

	public JdbcException(String message, Throwable cause) {
		super(message, cause);
	}

}