package com.harsh.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleResourceNotFound(ResourceNotFoundException ex){
		
		Map<String,Object> errorResponse = new HashMap<>();
		errorResponse.put("timeStamp", LocalDateTime.now());
		errorResponse.put("Error Message", ex.getMessage());
		errorResponse.put("Status", HttpStatus.NOT_FOUND.value());
		errorResponse.put("Error","Resource not Found");
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handleMemberNotFound(MemberNotFoundException ex){
		Map<String,Object> errorResponse = new HashMap<>();
		errorResponse.put("timeStamp", LocalDateTime.now());
		errorResponse.put("Error Message", ex.getMessage());
		errorResponse.put("Status", HttpStatus.NOT_FOUND.value());
		errorResponse.put("Error","Member not Found");
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
}
