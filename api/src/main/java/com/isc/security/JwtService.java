package com.isc.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {
	@Value("${jwt.secretKey}")
	private String secretkey;

	@Value("${jwt.timeExpiration}")
	private String timeExpiration;

	@Value("${jwt.resetPasswordExpiration}")
	private String resetPasswordExpiration;

	public String generateToken(String username) {
		return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
				.signWith(getSignInKey()).compact();
	}
	
	public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
	    Map<String, Object> claims = new HashMap<>();
	    List<String> authorityList = authorities.stream()
	        .map(GrantedAuthority::getAuthority)
	        .toList();

	    claims.put("authorities", authorityList);

	    return Jwts.builder()
	        .setClaims(claims)
	        .setSubject(username)
	        .setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeExpiration)))
	        .signWith(getSignInKey()).compact();
	}

	public String generatePasswordResetToken(String username) {
		return Jwts.builder().setSubject(username).claim("type", "password_reset")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(resetPasswordExpiration)))
				.signWith(getSignInKey()).compact();
	}
	
	public boolean isPasswordResetToken(String token) {
	    try {
	        Claims claims = extractAllClaims(token);
	        return "password_reset".equals(claims.get("type"));
	    } catch (Exception e) {
	        log.error("Invalid token: " + e.getMessage());
	        return false;
	    }
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public List<SimpleGrantedAuthority> extractAuthorities(String token) {
	        Claims claims = extractAllClaims(token);
	        @SuppressWarnings("unchecked")
	        List<String> authorityStrings = claims.get("authorities", List.class);
	        if (authorityStrings == null) {
	            return List.of(); // o Collections.emptyList()
	        }
	        return authorityStrings.stream()
	                .map(SimpleGrantedAuthority::new)
	                .toList();
	}
	
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretkey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
