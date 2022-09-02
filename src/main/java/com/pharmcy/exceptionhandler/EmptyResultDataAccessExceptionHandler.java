package com.pharmcy.exceptionhandler;

import org.springframework.dao.EmptyResultDataAccessException; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharmcy.dtoclass.ResponseDto;         
        
          

@RestControllerAdvice     
public class EmptyResultDataAccessExceptionHandler{  
	         
	@ExceptionHandler(value=EmptyResultDataAccessException.class)           
	public ResponseEntity<ResponseDto> handleInvalidCreditional(EmptyResultDataAccessException userexist) {         
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(       
				new ResponseDto(userexist.getMessage())          
				);        
	}        
                     
}               