package com.radovan.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.radovan.spring.exceptions.ExistingEmailException;
import com.radovan.spring.exceptions.InvalidUserException;
import com.radovan.spring.exceptions.SuspendedUserException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<String> handleInvalidUserException(InvalidUserException exc){
		return ResponseEntity.internalServerError().body("Invalid User!");
	}
	
	@ExceptionHandler(ExistingEmailException.class)
	public ResponseEntity<String> handleExistingEmailException(ExistingEmailException exc){
		return ResponseEntity.internalServerError().body("Email exists already!");
	}
	
	@ExceptionHandler(SuspendedUserException.class)
	public ResponseEntity<String> handleSuspendedUserException(SuspendedUserException exc){
		return ResponseEntity.internalServerError().body("Account suspended!");
	}
}
