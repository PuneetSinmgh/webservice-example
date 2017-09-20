package com.cybage.jiraservice.exception;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JiraExceptionResponse {
	private int status;
	private String message;
	
	public JiraExceptionResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public JiraExceptionResponse() {
		super();
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "JiraExceptionResponse [status=" + status + ", message=" + message + "]";
	}
	
}
