package com.isc.api.controllers;

import com.isc.api.dto.request.CompanyRequestDTO;
import com.isc.api.dto.response.CompanyDetailResponseDTO;
import com.isc.api.dto.response.CompanyResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.CompanyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory/api/v1/companies")
public class CompanyController
{
    private final CompanyService companyService;


    @GetMapping("/getAll")
public ResponseEntity<ResponseDto<List<CompanyDetailResponseDTO>>> getAllCompanies() 
    {
        return ResponseEntity.ok(companyService.getAllDetails());
    }

    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<CompanyResponseDTO>>> getSimpleList() 
    {
        return ResponseEntity.ok(companyService.getSimpleList());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<CompanyDetailResponseDTO>> createCompany(
            @Valid @RequestBody CompanyRequestDTO request) {
        return ResponseEntity.ok(companyService.save(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<CompanyDetailResponseDTO>> updateCompany(
            @PathVariable Integer id,
            @Valid @RequestBody CompanyRequestDTO request) 
    {
        return ResponseEntity.ok(companyService.update(request, id));
    }

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> deactivateCompany(@PathVariable Integer id) 
    {
        return ResponseEntity.ok(companyService.inactive(id));
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> activateCompany(@PathVariable Integer id) 
    {
        return ResponseEntity.ok(companyService.active(id));
    }
 }
