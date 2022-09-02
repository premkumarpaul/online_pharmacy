package com.pharmcy.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pharmcy.dtoclass.ResponseDto;

import online.pharmcy.exception.UserNotFoundException;

/**
 * @author PREMKUMAR PAUL
 *
 * USER NOT FOUND EXCEPTION HANDLER
 */
@RestControllerAdvice
public class UserNotFoundExceptionHandler {
	
	@ExceptionHandler(value=UserNotFoundException.class)
	public ResponseEntity<ResponseDto> handleInvalidCreditional(UserNotFoundException userexist) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ResponseDto(userexist.getMessage())
				);
	}

}