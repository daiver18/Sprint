package com.project.toDo.excepcion;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExcepcionGlobal extends ResponseEntityExceptionHandler {
	@ExceptionHandler(RecursoNoEncontrado.class)
	public ResponseEntity<?> RecursoNoEncontrado(RecursoNoEncontrado ex, WebRequest request) {
		DetallesError DetallesError = new DetallesError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(DetallesError, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		DetallesError DetallesError = new DetallesError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(DetallesError, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}