package com.isc.api.controllers;

import com.isc.api.dto.request.CustomerRequestDTO;
import com.isc.api.dto.request.CustomerUpdateRequestDTO;
import com.isc.api.dto.response.CustomerDetailResponseDTO;
import com.isc.api.dto.response.CustomerResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.CustomerService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ResponseDto<List<CustomerDetailResponseDTO>>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllDetails());
    }
    
    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<ResponseDto<CustomerDetailResponseDTO>> findByRuc(
            @PathVariable @Valid @NotBlank @Size(max = 13) String ruc) {
        return ResponseEntity.ok(customerService.findByRuc(ruc));
    }

    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<CustomerResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(customerService.getSimpleList());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<CustomerDetailResponseDTO>> createCustomer(
            @Valid @RequestBody CustomerRequestDTO request) {
        return ResponseEntity.ok(customerService.save(request));
    }

    @PutMapping("/update/{ruc}")
    public ResponseEntity<ResponseDto<CustomerDetailResponseDTO>> updateCustomer(
            @PathVariable String ruc,
            @Valid @RequestBody CustomerUpdateRequestDTO request) {
        
    	 return ResponseEntity.ok(customerService.update(request, ruc));    }

    @DeleteMapping("/inactive/{ruc}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> deactivateCustomer(@PathVariable String ruc) {
        return ResponseEntity.ok(customerService.inactive(ruc));
    }

    @PatchMapping("/activate/{ruc}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> activateCustomer(@PathVariable String ruc) {
        return ResponseEntity.ok(customerService.active(ruc));
    }
}
