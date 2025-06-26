
package com.isc.service;

import com.isc.dto.request.WarrantTypeRequest;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dtos.ResponseDto;

public interface WarrantTypeService {
    public ResponseDto<WarrantTypeDetailResponseDTO> save(WarrantTypeRequest request);
    public ResponseDto<WarrantTypeDetailResponseDTO> update(WarrantTypeRequest request, Integer id);
    public ResponseDto<MessageResponseDTO> inactive(Integer id);
    public ResponseDto<MessageResponseDTO> active(Integer id);
}
