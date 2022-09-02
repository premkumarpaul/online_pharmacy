package com.pharmacy.service;

/** THIS CLASS USES JWT TOKENS FOR RESPONSE * */
public class JwtResponse {
	private String token;

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
