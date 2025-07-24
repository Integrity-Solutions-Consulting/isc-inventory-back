package com.isc.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isc.api.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.api.dto.request.EquipmentRevokeRequestDTO;
import com.isc.api.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.api.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.EquipmentAssignmentService;

import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/v1/equipment-assignment")
public class EquipmentAssignmentController {

    @Autowired
    private EquipmentAssignmentService assignmentService;
   

    @GetMapping("/details")
    public ResponseEntity<ResponseDto<List<EquipmentAssignmentDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(assignmentService.getAllDetails());
    }

    @GetMapping("/simpleList")
    public ResponseEntity<ResponseDto<List<EquipmentAssignmentResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(assignmentService.getSimpleList());
    }
    
    @GetMapping("/available-ids")
    public ResponseEntity<ResponseDto<List<Integer>>> getAvailableEquipmentIds() {
        return ResponseEntity.ok(assignmentService.getAvailableEquipmentIds());
    }

    @PostMapping("/assign")
    public ResponseEntity<ResponseDto<EquipmentAssignmentDetailResponseDTO>> assign(
            @Valid@RequestBody EquipmentAssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.assign(request));
    }

    @PutMapping("/revoke/{id}")
    public ResponseEntity<ResponseDto<EquipmentAssignmentDetailResponseDTO>> revoke(
    		@PathVariable Integer id,
            @Valid@RequestBody EquipmentRevokeRequestDTO  request) {
        return ResponseEntity.ok(assignmentService.revoke(id,request));
    }



    // Desactivar asignación
    @PutMapping("/{id}/inactive")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(assignmentService.inactive(id));
    }

    // Activar asignación
    @PutMapping("/{id}/active")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(assignmentService.active(id));
    }
    
    @GetMapping("/{id}/report")
    public ResponseEntity<byte[]> downloadReport(@PathVariable Integer id) throws JRException {

        byte[] pdfBytes = assignmentService.generateReport(id);

        // 3. Configura headers para descargar el PDF
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition
            .attachment()
            .filename("reporte_equipo_" + id + ".pdf")
            .build());

        // 4. Retorna el ResponseEntity con el pdf y headers
        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }
}
