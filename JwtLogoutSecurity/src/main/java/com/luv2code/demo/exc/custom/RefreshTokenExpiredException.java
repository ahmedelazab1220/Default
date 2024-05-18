package com.luv2code.demo.exc.custom;

public class RefreshTokenExpiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RefreshTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public RefreshTokenExpiredException(String message) {
		super(message);
	}

	public RefreshTokenExpiredException(Throwable cause) {
		super(cause);
	}

}
