package com.isc.api.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.isc.api.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService
{
	private final JavaMailSender mailSender;

	private final String sender = "siaft@siaft.net";

	@Transactional
	@Override
	public void sendAssignmentEmailWithReport(String emailTo, byte[] report, String employeeName, String serialNumber, String categoryName) 
	{
	    try {
	        MimeMessage mensaje = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "utf-8");

	        String html = "<!DOCTYPE html>"
	                + "<html lang='es'>"
	                + "<head><meta charset='UTF-8'></head>"
	                + "<body>"
	                + "<h2>Asignación de equipo</h2>"
	                + "<p>Estimado/a <b>" + employeeName + "</b>,</p>"
	                + "<p>Se le ha asignado un equipo: <b>" + categoryName + "</b> "
	                + "con número de serie <b>" + serialNumber + "</b>.</p>"
	                + "<p>En el documento adjunto encontrará el detalle.</p>"
	                + "<p>Saludos,</p>"
	                + "<p><b>Área de RH</b></p>"
	                + "</body></html>";

	        helper.setTo("sanchezcordovajosedavid@gmail.com");
	        helper.setSubject("Asignación de equipo - " + categoryName + " - Serie: " + serialNumber);
	        helper.setText(html, true);
	        helper.setFrom(this.sender);

	        helper.addAttachment("asignacion-" + categoryName + "-" + serialNumber + ".pdf", new ByteArrayResource(report));

	        mailSender.send(mensaje);
	    } catch (MessagingException e) {
	        throw new RuntimeException("Error al enviar correo de asignación", e);
	    }
	}

}
