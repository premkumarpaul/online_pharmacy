package com.pharmacy.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.pharmacy.service.MyUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

/** THIS CLASS USES JWT TOKENS FOR GENERATING AND VALIDATING DETAILS**/
@Component
public class JwtUtil {
	
	@Value("${pharmacy.app.jwtSecret}")
	private String jwtSecret;

	@Value("${pharmacy.app.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	Logger logger
    = LoggerFactory.getLogger(JwtUtil.class);
	/**IT IS FOR GENERATING JWT TOKEN **/
	public String generateJwtToken(Authentication authentication) {

		MyUserDetails userPrincipal = (MyUserDetails) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.claim("username",userPrincipal.getUsername())
				.claim("role", userPrincipal.getRole())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
	/**IT IS FOR VALIDATING JWT TOKEN**/
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.info (String.format("Invalid JWT signature:{}", e.getMessage()));
		} catch (MalformedJwtException e) {
			logger.info(String.format("Invalid JWT token:{}", e.getMessage()));
		} catch (ExpiredJwtException e) {
			logger.info (String.format("JWT token is expired:{}", e.getMessage()));
		} catch (UnsupportedJwtException e) {
			logger.info (String.format("JWT token is unsupported:{}",e.getMessage()));
		} catch (IllegalArgumentException e) {
			logger.info (String.format("JWT claims string is empty:{}",e.getMessage()));
		}

		return false;
	}
}
