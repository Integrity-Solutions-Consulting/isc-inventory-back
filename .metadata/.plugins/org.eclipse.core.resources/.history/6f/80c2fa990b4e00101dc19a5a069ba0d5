package com.isc.controllers;

import com.isc.dto.request.CompanyRequestDTO;
import com.isc.dto.response.CompanyDetailResponseDTO;
import com.isc.dto.response.CompanyResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.CompanyService;

import jakarta.validation.Valid;

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
@RequestMapping("/api/companies")
public class CompanyController
{
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<CompanyDetailResponseDTO>>> getAllCompanies() 
    {
        return ResponseEntity.ok(companyService.getAllDetails());
    }

    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<CompanyResponseDTO>>> getSimpleList() 
    {
        return ResponseEntity.ok(companyService.getSimpleList());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<CompanyDetailResponseDTO>> createCompany(
            @Valid @RequestBody CompanyRequestDTO request) {
        return ResponseEntity.ok(companyService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<CompanyDetailResponseDTO>> updateCompany(
            @PathVariable Integer id,
            @Valid @RequestBody CompanyRequestDTO request) 
    {
        return ResponseEntity.ok(companyService.update(request, id));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> deactivateCompany(@PathVariable Integer id) 
    {
        return ResponseEntity.ok(companyService.inactive(id));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> activateCompany(@PathVariable Integer id) 
    {
        return ResponseEntity.ok(companyService.active(id));
    }
 }
