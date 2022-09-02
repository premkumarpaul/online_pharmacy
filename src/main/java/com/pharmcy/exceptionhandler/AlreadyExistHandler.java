package com.pharmcy.exceptionhandler;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.RestControllerAdvice; 


import com.pharmcy.dtoclass.ResponseDto;

import online.pharmcy.exception.AlreadyExistException; 


/**
 * @author PREMKUMAR PAUL
 *
 * USER ALREADY PRESENT EXCEPTION HANDLER
 */
@RestControllerAdvice 
public class AlreadyExistHandler { 
	@ExceptionHandler(value=AlreadyExistException.class)  
	public ResponseEntity<ResponseDto> handleUserAlreadyExist(AlreadyExistException userexist) { 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( 
				new ResponseDto(userexist.getMessage()) 
				); 
	} 

	

}  