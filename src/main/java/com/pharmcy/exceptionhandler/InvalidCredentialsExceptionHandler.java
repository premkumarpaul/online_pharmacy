package com.pharmcy.exceptionhandler;


import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharmcy.dtoclass.ResponseDto;

import online.pharmcy.exception.InvalidCredentialsException; 

/**
 * @author PREMKUMAR PAUL
 *
 * INVALID USERNAME AND PASSWORD EXCEPTION HANDLER
 */
@RestControllerAdvice 
public class InvalidCredentialsExceptionHandler { 
	
	
	@ExceptionHandler(value=InvalidCredentialsException.class) 
	public ResponseEntity<ResponseDto> handleInvalidCreditional(InvalidCredentialsException userexist) { 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( 
				new ResponseDto(userexist.getMessage())  
				); 
	} 
	 

} 