package com.backend.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
		List<String> errors = Arrays.asList(userMessage, developerMessage);
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, errorMessage, headers, errorMessage.getStatus(), request);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = createErrorList(ex.getBindingResult());
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, errorMessage, headers, errorMessage.getStatus(), request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		String userMessage = "userMessage: "
				+ messageSource.getMessage("resource.not.found", null, LocaleContextHolder.getLocale());
		String developerMessage = "developerMessage: " + ex.toString();
		List<String> errors = Arrays.asList(userMessage, developerMessage);
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), errorMessage.getStatus(), request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String userMessage = "userMessage: "
				+ messageSource.getMessage("feature.operation.not.allowed", null, LocaleContextHolder.getLocale());
		String developerMessage = "developerMessage: " + ExceptionUtils.getRootCauseMessage(ex);
		List<String> errors = Arrays.asList(userMessage, developerMessage);
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), errorMessage.getStatus(), request);
	}

//	@ExceptionHandler({ EmailUserAlreadyRegisteredException.class })
//	public ResponseEntity<Object> handleEmailUserAlreadyRegisteredException(EmailUserAlreadyRegisteredException ex,
//			WebRequest request) {
//		String userMessage = "userMessage: "
//				+ messageSource.getMessage("email.not.available", null, LocaleContextHolder.getLocale());
//		String developerMessage = "developerMessage: " + ExceptionUtils.getRootCauseMessage(ex);
//		List<String> errors = Arrays.asList(userMessage, developerMessage);
//		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
//		return handleExceptionInternal(ex, errorMessage, new HttpHeaders(), errorMessage.getStatus(), request);
//	}

	private List<String> createErrorList(BindingResult bindingResult) {
		List<String> errors = new ArrayList<>();

		for (FieldError error : bindingResult.getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (ObjectError error : bindingResult.getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		return errors;
	}

}
