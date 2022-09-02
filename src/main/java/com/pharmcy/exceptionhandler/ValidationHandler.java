package com.pharmcy.exceptionhandler;

import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.RestControllerAdvice; 
import org.springframework.validation.FieldError; 
import org.springframework.web.bind.MethodArgumentNotValidException; 
import java.util.Map;import java.util.HashMap; 

@RestControllerAdvice 
public class ValidationHandler { 
	
	@ExceptionHandler(value=MethodArgumentNotValidException.class) 
	public ResponseEntity<Object> handleUserAlreadyExist(MethodArgumentNotValidException ex) { 

          Map<String,String> errors=new HashMap<>(); 
          ex.getBindingResult().getAllErrors().forEach((error)->{ 
		  
              String fieldName=((FieldError) error).getField(); 
              String message=error.getDefaultMessage(); 
              errors.put(fieldName,message); 
          }); 
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST); 
	}  
 
	

} 