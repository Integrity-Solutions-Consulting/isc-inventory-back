package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isc.api.dto.response.DashboardAcquisitionResponseDTO;
import com.isc.api.dto.response.DashboardEquipmentAssignedByCategoryResponseDTO;
import com.isc.api.dto.response.DashboardEquipmentStatusSummaryResponseDTO;
import com.isc.api.dto.response.DashboardResponseDTO;
import com.isc.api.service.DashboardService;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

	private final DashboardService dashboardService;
	
	@GetMapping("/cards")
	public ResponseEntity<ResponseDto<DashboardResponseDTO>> getCards(){
		return ResponseEntity.ok(dashboardService.getCards());
	}
	
	@GetMapping("/barChart")
	public ResponseEntity<ResponseDto<List<DashboardEquipmentAssignedByCategoryResponseDTO>>> getBar(){
		return ResponseEntity.ok(dashboardService.getEquipmentAssignedByCategory());
	}
	
	@GetMapping("/pieChart")
	public ResponseEntity<ResponseDto<List<DashboardEquipmentStatusSummaryResponseDTO>>> getPie(@RequestParam("category") Integer categoryId){
		return ResponseEntity.ok(dashboardService.getEquipmentStatusSummary(categoryId));
	}
	
	@GetMapping("/lineChart")
	public ResponseEntity<ResponseDto<List<DashboardAcquisitionResponseDTO>>> getLine(@RequestParam("year") Integer year){
		return ResponseEntity.ok(dashboardService.getAssetAcquisitionTrends(year));
	}
}
