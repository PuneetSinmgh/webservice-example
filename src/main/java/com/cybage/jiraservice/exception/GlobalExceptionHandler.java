package com.cybage.jiraservice.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	public GlobalExceptionHandler() {
		super();
	}
  
	@ExceptionHandler({JiraException.class})
	public ResponseEntity<JiraExceptionResponse> handleException(JiraException ex, WebRequest request ){
		JiraExceptionResponse jiraExceptionResponse = applicationContext.getBean(JiraExceptionResponse.class);
		jiraExceptionResponse.setMessage(ex.getMessage());
		jiraExceptionResponse.setStatus(ex.getStatus());
		logger.info("Message : " + ex.getMessage());
		logger.info("Status : " + ex.getStatus());
		return new ResponseEntity<>(jiraExceptionResponse,HttpStatus.valueOf(jiraExceptionResponse.getStatus()));
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<JiraExceptionResponse> handleException(Exception ex){
		JiraExceptionResponse jiraExceptionResponse = applicationContext.getBean(JiraExceptionResponse.class);
		jiraExceptionResponse.setMessage(ex.getMessage());
		jiraExceptionResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		logger.info("jiraExceptionResponse : " + jiraExceptionResponse);
		return new ResponseEntity<>(jiraExceptionResponse,HttpStatus.valueOf(jiraExceptionResponse.getStatus()));
	}
	
}