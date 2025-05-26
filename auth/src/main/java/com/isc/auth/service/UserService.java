package com.isc.auth.service;

import java.util.List;

import com.isc.auth.dto.request.PasswordChangeRequestDTO;
import com.isc.auth.dto.request.UserRequestoDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.UserResponseDTO;
import com.isc.dtos.ResponseDto;

public interface UserService {
	public ResponseDto<List<UserResponseDTO>> getAll();
	public ResponseDto<UserResponseDTO> updateUser(UserRequestoDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> changePassword(PasswordChangeRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> deleteUser(Integer id);
	public ResponseDto<MessageResponseDTO> activeUser(Integer id);
	public ResponseDto<MessageResponseDTO> suspendUser(Integer id);
	public ResponseDto<MessageResponseDTO> unsuspendUser(Integer id);
}
