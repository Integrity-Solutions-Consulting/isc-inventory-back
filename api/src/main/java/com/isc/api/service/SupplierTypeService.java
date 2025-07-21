package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.response.SupplierTypeResponseDTO;
import com.isc.dtos.ResponseDto;

public interface SupplierTypeService 
{
    ResponseDto<List<SupplierTypeResponseDTO>>getAllActive();
}
