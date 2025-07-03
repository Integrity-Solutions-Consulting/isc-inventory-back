package com.isc.auth.service;

import java.util.List;

import com.isc.auth.dto.request.PrivilegeRequestDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.dtos.ResponseDto;

public interface PrivilegeService {
	public ResponseDto<List<PrivilegeResponseDTO>> getAll();
	public ResponseDto<PrivilegeResponseDTO> save(PrivilegeRequestDTO request);
	public ResponseDto<MessageResponseDTO> inactivePrivilege(Integer id);
	public ResponseDto<MessageResponseDTO> activePrivilege(Integer id);
}
