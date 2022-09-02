package com.pharmcy.exceptionhandler;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharmcy.dtoclass.ResponseDto;

import online.pharmcy.exception.DatabaseTranscationException; 

/**
 * @author PREMKUMAR PAUL
 * IF THE DATA IS NOT FOUND IN THE DATABASE EXECPTION HANDLER
 */
@RestControllerAdvice 
public class DatabaseTranscationExceptionHandler { 
	@ExceptionHandler(value=DatabaseTranscationException.class) 
	public ResponseEntity<ResponseDto> handleDatabaseException(DatabaseTranscationException userexist) { 
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body( 
				new ResponseDto(userexist.getMessage()) 
				); 
	} 
}  