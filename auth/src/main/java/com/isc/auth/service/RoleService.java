package com.isc.auth.service;

import java.util.List;

import com.isc.auth.dto.request.RoleRequestDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.dtos.ResponseDto;

public interface RoleService {
	public ResponseDto<List<RolesResponseDTO>> getAll();
	public ResponseDto<RolesResponseDTO> save(RoleRequestDTO request);
	public ResponseDto<RolesResponseDTO> addPrivileges(RoleRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactiveRole(Integer id);
	public ResponseDto<MessageResponseDTO> activeRole(Integer id);
}
