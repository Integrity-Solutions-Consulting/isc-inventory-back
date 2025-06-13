package com.isc.controllers;

import com.isc.dto.request.AppearanceRequestDTO;
import com.isc.dto.response.AppearanceDetailResponseDTO;
import com.isc.dto.response.AppearanceResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.AppearanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/appearance")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AppearanceController 
{

    private final AppearanceService service;

    @GetMapping("/getAllDetails")
    public ResponseEntity<ResponseDto<List<AppearanceDetailResponseDTO>>> getAllDetails() 
    {
        return ResponseEntity.ok(service.getAllDetails());
    }

    @GetMapping("/getSimpleList")
    public ResponseEntity<ResponseDto<List<AppearanceResponseDTO>>> getSimpleList() 
    {
        return ResponseEntity.ok(service.getSimpleList());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<AppearanceDetailResponseDTO>> save(@Valid @RequestBody AppearanceRequestDTO request) 
    {
        return ResponseEntity.ok(service.save(request));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<AppearanceDetailResponseDTO>> update(@Valid @RequestBody AppearanceRequestDTO request,
                                                                           @PathVariable Integer id) 
    {
        return ResponseEntity.ok(service.update(request, id));
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> activate(@PathVariable Integer id) 
    {
        return ResponseEntity.ok(service.active(id));
    }

    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) 
    {
        return ResponseEntity.ok(service.inactive(id));
    }
}
