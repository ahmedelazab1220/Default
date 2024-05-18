package com.luv2code.demo.exc.custom;

public class NotFoundRefreshTokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundRefreshTokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundRefreshTokenException(String message) {
		super(message);
	}

	public NotFoundRefreshTokenException(Throwable cause) {
		super(cause);
	}

}
