package com.isc.api.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.isc.api.dto.request.AppearanceRequestDTO;
import com.isc.api.dto.response.AppearanceDetailResponseDTO;
import com.isc.api.dto.response.AppearanceResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.AppearanceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/appearance")
@RequiredArgsConstructor

public class AppearanceController 
{
    
    private final AppearanceService aperreanceService;
    
    @GetMapping("/allDetailsList")
    public ResponseEntity<ResponseDto<List<AppearanceDetailResponseDTO>>> getAllDetails() 
    {
        return ResponseEntity.ok(aperreanceService.getAllDetails());
    }
    
    @GetMapping("/simpleList")
    public ResponseEntity<ResponseDto<List<AppearanceResponseDTO>>> getSimpleList() 
    {
        return ResponseEntity.ok(aperreanceService.getSimpleList());
    }
    
    @PostMapping("/createAppearance")
    public ResponseEntity<ResponseDto<AppearanceDetailResponseDTO>> createAppearance(
            @Valid @RequestBody AppearanceRequestDTO request) 
    {
        return ResponseEntity.ok(aperreanceService.save(request));
    }
   
    @PutMapping("/{id}/updateAppearance")
    public ResponseEntity<ResponseDto<AppearanceDetailResponseDTO>> updateAppearance(
            @PathVariable Integer id,
            @Valid @RequestBody AppearanceRequestDTO request) 
    {
        return ResponseEntity.ok(aperreanceService.update(request, id));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> activateAppearance(@PathVariable Integer id) 
    {
        return ResponseEntity.ok(aperreanceService.active(id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> deactivateAppearance(@PathVariable Integer id) 
    {
        return ResponseEntity.ok(aperreanceService.inactive(id));
    }
}