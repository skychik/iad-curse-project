package ru.ifmo.cs.iad.iadcurseproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private Logger logger = LoggerFactory.getLogger("RestResponseEntityExceptionHandler");

	@ExceptionHandler(value = { IllegalArgumentException.class/*, IllegalStateException.class*/ })
	protected ResponseEntity<Object> handleIllegalArgumentException (RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Illegal argument";
		logger.info(bodyOfResponse);
		logger.info(handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request)
				.toString());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

//	@ExceptionHandler(value = { UnauthorizedException.class })
//	protected ResponseEntity handleUnauthorizedException (UnauthorizedException ex, WebRequest request) {
//		String bodyOfResponse = "Illegal argument";
//		logger.info(bodyOfResponse);
//		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
//	}
}