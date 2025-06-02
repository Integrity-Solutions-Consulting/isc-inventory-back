package com.isc.service;

import java.util.List;

import com.isc.dto.request.EmployeeRequestDTO;
import com.isc.dto.request.GenderRequestDTO;
import com.isc.dto.response.EmployeeCatalogResponseDTO;
import com.isc.dto.response.EmployeeDetailResponseDTO;
import com.isc.dto.response.EmployeeTableResponseDTO;
import com.isc.dto.response.GenderDetailResponseDTO;
import com.isc.dto.response.GenderResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EmployeeService {
	public ResponseDto<List<EmployeeTableResponseDTO>> getAllTable();
	public ResponseDto<List<EmployeeCatalogResponseDTO>> getSimpleList();
	public ResponseDto<EmployeeDetailResponseDTO> getInfoById(Integer id);
	public ResponseDto<EmployeeDetailResponseDTO> save(EmployeeRequestDTO request);
	public ResponseDto<EmployeeDetailResponseDTO> update(EmployeeRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
