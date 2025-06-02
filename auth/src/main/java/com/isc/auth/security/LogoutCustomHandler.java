package com.isc.auth.security;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.isc.auth.entitys.TokenBlackListEntity;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.repository.TokenBlackListRepository;
import com.isc.auth.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LogoutCustomHandler implements LogoutHandler {

	private final JwtService jwtService;
	private final UserRepository userRepository;
	private final TokenBlackListRepository blackListRepository;
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			if (jwtService.isTokenValid(token)) {
				String username = jwtService.extractUsername(token);
				blackListRepository.save(new TokenBlackListEntity(null,token));
				UserEntity user = userRepository.findByUsername(username).orElseThrow();
				user.setLoggedIn(false);
				userRepository.save(user);
			}
		}
		SecurityContextHolder.clearContext();
	}
}
