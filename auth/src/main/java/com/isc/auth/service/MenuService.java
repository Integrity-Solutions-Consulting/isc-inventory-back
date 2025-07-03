package com.isc.auth.service;

import java.util.List;

import com.isc.auth.dto.request.MenuRequestDTO;
import com.isc.auth.dto.response.MenuResponseDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface MenuService {
	public ResponseDto<List<MenuResponseDTO>> getAll();
	public ResponseDto<List<MenuResponseDTO>> getAllByRoleId(Integer roleId);
	public ResponseDto<List<MenuResponseDTO>> getAllByUserId(Integer userId);
	public ResponseDto<MenuResponseDTO> save(MenuRequestDTO request);
	public ResponseDto<MenuResponseDTO> update(MenuRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
