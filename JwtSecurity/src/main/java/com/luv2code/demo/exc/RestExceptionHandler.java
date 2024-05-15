package com.luv2code.demo.exc;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(AccessDeniedException.class)
	ErrorResponse handleAccessDeniedException(Exception ex , WebRequest request) {
		return new ErrorResponse(StatusCode.FORBIDDEN , "Access Denied" , "No Permission." , ex.getMessage() , request.getDescription(false).substring(4) , System.currentTimeMillis());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	ErrorResponse handleDuplicateException(DataIntegrityViolationException ex , WebRequest request) {
		return new ErrorResponse(StatusCode.Conflict , "Data Integrity Violation" , "Username is exist.", ex.getMessage(), request.getDescription(false).substring(4) , System.currentTimeMillis());
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ErrorResponse handleSecurityException(BadCredentialsException ex , WebRequest request) {
		return new ErrorResponse(StatusCode.UNAUTHORIZED , "Bad Credentials" , "username or password is incorrect." , request.getDescription(false).substring(4) , ex.getMessage(),System.currentTimeMillis());
	}
	
	@ExceptionHandler(SignatureException.class)
	public ErrorResponse handleSignatureException(SignatureException ex , WebRequest request) {
		return new ErrorResponse(StatusCode.FORBIDDEN , "Signature", "The JWT signature is invalid." , ex.getMessage() , request.getDescription(false).substring(4) ,System.currentTimeMillis());
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ErrorResponse handleExpiredJwtxception(ExpiredJwtException ex , WebRequest request) {
		return new ErrorResponse(StatusCode.FORBIDDEN , "ExpiredJwt" , "The JWT token has expired." , ex.getMessage() , request.getDescription(false).substring(4) ,System.currentTimeMillis());
	}
	
	@ExceptionHandler(InternalServerError.class)
	public ErrorResponse handleInternalServerException(InternalServerError ex , WebRequest request) {
		return new ErrorResponse(StatusCode.INTERNAL_SERVER_ERROR , "InternalServerError" , "Unknown internal server error." , ex.getMessage() , request.getDescription(false).substring(4) ,System.currentTimeMillis());
	}
	
	@ExceptionHandler(InsufficientAuthenticationException.class)
	public ErrorResponse handleException(InsufficientAuthenticationException ex , WebRequest request) {
		return new ErrorResponse(StatusCode.INTERNAL_SERVER_ERROR , "Jwt Exception", "Login credentials are missing." , ex.getMessage() , request.getDescription(false).substring(4) ,System.currentTimeMillis());
	}
	
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception ex , WebRequest request) {
		return new ErrorResponse(StatusCode.FORBIDDEN , "Exception", "Unknown Exception." , ex.getMessage() , request.getDescription(false).substring(4) ,System.currentTimeMillis());
	}
}
