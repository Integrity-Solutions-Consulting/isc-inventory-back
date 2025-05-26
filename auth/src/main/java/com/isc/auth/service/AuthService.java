package com.isc.auth.service;

import java.util.Map;

import com.isc.auth.dto.request.PasswordChangeRequestDTO;
import com.isc.auth.dto.request.UserRequestoDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.TokenResponseDTO;
import com.isc.auth.dto.response.UserRegisterResponseDTO;
import com.isc.dtos.ResponseDto;

public interface AuthService {
	public ResponseDto<UserRegisterResponseDTO> register(UserRequestoDTO request);
	public ResponseDto<TokenResponseDTO> generateTokenForgotPassword(String username);
	public ResponseDto<MessageResponseDTO> validateTokenForgotPassword(String token);
	public ResponseDto<Boolean> restorePassword(String token, PasswordChangeRequestDTO request) ;
}
