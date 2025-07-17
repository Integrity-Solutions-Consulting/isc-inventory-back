package com.isc.api.service;

import com.isc.api.entitys.EquipmentAssignmentEntity;

import net.sf.jasperreports.engine.JRException;

public interface ReportService {
	public byte[] generateReport(EquipmentAssignmentEntity assigment) throws JRException;
}
