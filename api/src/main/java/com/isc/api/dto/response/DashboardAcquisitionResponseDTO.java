package com.isc.api.dto.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardAcquisitionResponseDTO {
	private Integer numeroMes;
    private String nombreMes;
    private Long conteoAdquisiciones;
    private BigDecimal valorTotalAdquisiciones;
}
