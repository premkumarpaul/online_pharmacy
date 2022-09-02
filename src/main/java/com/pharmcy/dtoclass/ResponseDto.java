package com.pharmcy.dtoclass;


/**
 * @author PREMKUMAR PAUL
 *
 * 
 */
public class ResponseDto { 

	private String message; 
	
	public String getMessage() { 
		return message; 
	}
	public void setMessage(String message) { 
		this.message = message; 
	}

	public ResponseDto(String message) { 
		super(); 
		this.message = message; 
		
	} 
	public ResponseDto() { 
		super(); 
	} 
	
	
} 
