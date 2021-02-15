package com.example.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExpentionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException
	(UserNotFoundException exception,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false),404);
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<?> handleAPIException
	(APIException exception,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false),404);
		return new ResponseEntity(errorDetails,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException
	(Exception exception,WebRequest request){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(),exception.getMessage(),request.getDescription(false),500);
		return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
