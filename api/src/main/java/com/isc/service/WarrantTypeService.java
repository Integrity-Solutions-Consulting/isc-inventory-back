
package com.isc.service;

import com.isc.dto.request.WarrantTypeRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.WarrantTypeEntity;

public interface WarrantTypeService {
	WarrantTypeEntity save(WarrantTypeRequestDTO request);
	WarrantTypeEntity update(WarrantTypeRequestDTO request, Integer idWarranty);
    public ResponseDto<MessageResponseDTO> inactive(Integer id);
    public ResponseDto<MessageResponseDTO> active(Integer id);
}
