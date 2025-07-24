package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EmployeeRequestDTO;
import com.isc.api.dto.response.EmployeeCatalogResponseDTO;
import com.isc.api.dto.response.EmployeeDetailResponseDTO;
import com.isc.api.dto.response.EmployeeTableResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EmployeeService {
	public ResponseDto<List<EmployeeTableResponseDTO>> getAllTable();
	public ResponseDto<List<EmployeeCatalogResponseDTO>> getSimpleList();
	public ResponseDto<EmployeeDetailResponseDTO> getInfoById(Integer id);
	public ResponseDto<EmployeeTableResponseDTO> save(EmployeeRequestDTO request);
	public ResponseDto<EmployeeTableResponseDTO> update(EmployeeRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
