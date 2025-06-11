package com.isc.service;

import java.util.List;

import com.isc.dto.request.CustomerRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.CustomerDetailResponseDTO;
import com.isc.dto.response.CustomerResponseDTO;
import com.isc.dtos.ResponseDto;

public interface CustomerService {
	public ResponseDto<List<CustomerDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<CustomerResponseDTO>> getSimpleList();
	public ResponseDto<CustomerDetailResponseDTO> save(CustomerRequestDTO request);
	public ResponseDto<CustomerDetailResponseDTO> update(CustomerRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
