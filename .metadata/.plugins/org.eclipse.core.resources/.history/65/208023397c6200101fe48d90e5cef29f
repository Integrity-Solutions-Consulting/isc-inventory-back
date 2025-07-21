package com.isc.api.service;

import java.io.InputStream;
import java.util.Map;

import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class ReportService {
		public byte[] generateReport(String reportName) throws JRException
		{
			//Cargar el report
			InputStream reportStream= this.getClass().getResourceAsStream("/reports/"+reportName+".jasper");
			
			Map<String,Object>parms=null;
			//llenado 
			JasperPrint jasperPint= JasperFillManager.fillReport(reportStream,parms,new JREmptyDataSource());
			//exporta a un report a tipo pdf
			return JasperExportManager.exportReportToPdf(jasperPint);
		}
}
