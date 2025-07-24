package com.isc.api.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.isc.api.dto.response.EquipmentCharacteristicsReportResponseDTO;
import com.isc.api.entitys.EquipmentAssignmentEntity;
import com.isc.api.entitys.EquipmentEntity;
import com.isc.api.mapper.EquipmentCharacteristicMapper;
import com.isc.api.service.ReportService;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ResourceLoader resourceLoader;

    @Override
    public byte[] generateReport(EquipmentAssignmentEntity assigment) throws JRException {

        try (InputStream reportStream = resourceLoader.getResource("classpath:templates/report/report.jrxml").getInputStream()) {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            Map<String, Object> params = new HashMap<>();
            params.put("employeeName", assigment.getEmployee().getFirstName() + " " + assigment.getEmployee().getLastName());
            params.put("employeeId", assigment.getEmployee().getIdentification());
            params.put("employeeIdType", assigment.getEmployee().getIdentificationType().getDescription());
            params.put("itemCode", assigment.getEquipment().getItemCode());
            params.put("serialNumber", assigment.getEquipment().getSerialNumber());
            params.put("brand", assigment.getEquipment().getBrand());
            params.put("model", assigment.getEquipment().getModel());
            params.put("date", assigment.getAssignmentDate());

            params.put("logo", loadPngImage());

            List<EquipmentCharacteristicsReportResponseDTO> dtos =
                    assigment.getEquipment().getCharacteristic().stream()
                            .map(EquipmentCharacteristicMapper::toReportDto)
                            .collect(Collectors.toList());

            JRBeanCollectionDataSource caracteristicasDataSource = new JRBeanCollectionDataSource(dtos);
            params.put("caracteristicas", caracteristicasDataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generando el reporte", e);
        }
    }

    private BufferedImage loadPngImage() throws Exception {
        Resource logoResource = resourceLoader.getResource("classpath:logo/logo.png");
        try (InputStream is = logoResource.getInputStream()) {
            if (is == null) {
                throw new IllegalArgumentException("No se encontró logo.png en classpath:logo/");
            }
            return ImageIO.read(is);
        }
    }
}
