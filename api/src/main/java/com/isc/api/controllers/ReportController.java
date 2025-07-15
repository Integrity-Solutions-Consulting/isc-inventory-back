package com.isc.api.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isc.api.service.ReportService; 

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/report")
@RequiredArgsConstructor

public class ReportController 
{
	private ReportService reportService;
	
	
	@GetMapping("/report")
	public ResponseEntity<byte[]>generarReporte(){
		try {
				byte[] report= reportService.generateReport("reportPdf");
				HttpHeaders headers=new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_PDF);
				
				headers.add("Content-Disposition","inline-filename=report.pdf");
				
				return new ResponseEntity<>(report, headers, HttpStatus.OK);
			
		}catch(Exception e)
		{ 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
			
		}
	}
	

}
