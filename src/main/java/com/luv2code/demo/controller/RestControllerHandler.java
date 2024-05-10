package com.luv2code.demo.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.luv2code.demo.exc.ErrorResponse;
import com.luv2code.demo.exc.StatusCode;

@RestControllerAdvice
public class RestControllerHandler {

	@ExceptionHandler({UsernameNotFoundException.class, BadCredentialsException.class })
	public ErrorResponse handleSecurityException(Exception ex) {
		return new ErrorResponse(StatusCode.UNAUTHORIZED, "username or password is incorrect.",
				System.currentTimeMillis());
	}

	@ExceptionHandler(AccessDeniedException.class)
	ErrorResponse handleAccessDeniedException(AccessDeniedException ex) {
		return new ErrorResponse(StatusCode.FORBIDDEN, "No permission.", System.currentTimeMillis());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	ErrorResponse handleDuplicateException(DataIntegrityViolationException ex) {
		return new ErrorResponse(StatusCode.Conflict, "Username is exist", System.currentTimeMillis());
	}
}
