package com.b2w.apistarwars.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.b2w.apistarwars.exception.BadRequest;
import com.b2w.apistarwars.exception.ObjectNotFoundException;
import com.b2w.apistarwars.exception.ServiceUnavailable;

import com.b2w.apistarwars.models.StandardError;

@ControllerAdvice
public class ControlExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e,HttpServletRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	 
	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<StandardError> badRequest(BadRequest e,HttpServletRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Requisição inválida", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ServiceUnavailable.class)
	public ResponseEntity<StandardError> servicUnavailable(ServiceUnavailable e,HttpServletRequest request){
		
		HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Serviço Indisponível", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
