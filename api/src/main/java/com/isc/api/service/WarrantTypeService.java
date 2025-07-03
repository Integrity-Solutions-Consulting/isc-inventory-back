
package com.isc.api.service;

import com.isc.api.dto.request.WarrantTypeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.WarrantTypeEntity;

public interface WarrantTypeService {
	WarrantTypeEntity save(WarrantTypeRequestDTO request);
	WarrantTypeEntity update(WarrantTypeRequestDTO request, Integer idWarranty);
    public ResponseDto<MessageResponseDTO> inactive(Integer id);
    public ResponseDto<MessageResponseDTO> active(Integer id);
}
