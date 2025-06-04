package com.isc.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.isc.repository.TokenBlacklistRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private TokenBlacklistRepository blackListRepository;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

    	String tokenHeader = request.getHeader("Authorization");

    	if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
    	    String token = tokenHeader.substring(7);

    	    // Validar si el token está en la lista negra
    	    if (blackListRepository.findByToken(token).isPresent()) {
    	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	        response.getWriter().write("Token inválido o revocado");
    	        return;
    	    }

    	    if (jwtService.isTokenValid(token)) {
    	        String username = jwtService.extractUsername(token);
    	        List<SimpleGrantedAuthority> authorities = jwtService.extractAuthorities(token);

    	        UserDetails userDetails = new User(
    	            username, "", authorities
    	        );

    	        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
    	            userDetails, null, authorities
    	        );

    	        SecurityContextHolder.getContext().setAuthentication(authToken);
    	    }
    	}

    	filterChain.doFilter(request, response);
    }

}
