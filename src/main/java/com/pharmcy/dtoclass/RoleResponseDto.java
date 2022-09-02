package com.pharmcy.dtoclass;

public class RoleResponseDto {


	private String role;
	
	public String getRole() {
		return role;
	}
	public void setMessage(String role) {
		this.role = role;
	}

	public RoleResponseDto(String role) {
		super();
		this.role = role;
		
	}
	public RoleResponseDto() {
		super();
	}
	
	
}