package com.backend.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

	private HttpStatus status;
	private String userMessage;
	private List<String> errors;

	public ErrorMessage(HttpStatus status, String userMessage, List<String> errors) {
		this.status = status;
		this.userMessage = userMessage;
		this.errors = errors;
	}

	public ErrorMessage(HttpStatus status, String userMessage, String error) {
		this.status = status;
		this.userMessage = userMessage;
		this.errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
