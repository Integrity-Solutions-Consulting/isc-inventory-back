package com.isc.auth.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.isc.auth.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;
	
	private final String sender = "siaft@siaft.net";
	
	@Value("${frontend.url}")
	private String serverUrl;
	
	private final String frontUrl = serverUrl+"auth/forgot-password?token=";

	@Transactional
	@Override
	public void sendForgotPasswordEmail(String emailTo, String token) {
		try {
			MimeMessage mensaje = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mensaje, "utf-8");

			String html = "<!DOCTYPE html>"
			        + "<html lang='es'>"
			        + "<head>"
			        + "<meta charset='UTF-8'>"
			        + "<title>Recuperación de contraseña</title>"
			        + "<style>"
			        + "body { margin: 0; padding: 0; background-color: #f2f2f2; font-family: 'Raleway', sans-serif; color: #0d0d0d; }"
			        + ".container { width: 100%; padding: 30px 0; display: flex; justify-content: center; align-items: center; }"
			        + ".card { background-color: #ffffff; padding: 30px; max-width: 500px; width: 90%; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); border-radius: 12px; text-align: center; }"
			        + ".logo { width: 150px; margin-bottom: 20px; }"
			        + ".title { font-family: 'Poppins', sans-serif; font-size: 22px; font-weight: bold; color: #1c4d8c; margin-bottom: 10px; }"
			        + ".subtitle { font-family: 'League Spartan', sans-serif; font-size: 16px; color: #555; margin-bottom: 20px; }"
			        + ".button { display: inline-block; margin-top: 20px; padding: 12px 20px; background-color: #1c4d8c; color: #ffffff; text-decoration: none; border-radius: 6px; font-weight: bold; }"
			        + ".footer { font-size: 13px; color: #666; margin-top: 30px; }"
			        + "</style>"
			        + "</head>"
			        + "<body>"
			        + "<div class='container'>"
			        + "<div class='card'>"
			        + "<img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8ZCctTKfGFdHZHIYBnQqcSkHUvs9khINITA&s' alt='Logo de la compañía' class='logo'>"
			        + "<div class='title'>Recuperación de contraseña</div>"
			        + "<div class='subtitle'>Has solicitado restablecer tu contraseña.</div>"
			        + "<p>Haz clic en el siguiente botón para continuar con el proceso:</p>"
			        + "<a href='" + frontUrl + token + "' class='button'>Restablecer contraseña</a>"
			        + "<p class='footer'>Este enlace expirará en 3 minutos.<br>Si no solicitaste este cambio, puedes ignorar este mensaje.</p>"
			        + "</div>"
			        + "</div>"
			        + "</body>"
			        + "</html>";

			helper.setTo("kerly.jimenez@integritysolutions.com.ec");
			helper.setSubject("Recuperación de contraseña");
			helper.setText(html, true); // segundo parámetro `true` indica que es HTML
			helper.setFrom(this.sender);
			mailSender.send(mensaje);
		} catch (MessagingException e) {
			throw new RuntimeException("Error al enviar el mensaje");
		}
	}
	
	@Transactional
	@Override
	public void sendUserCreatedEmail(String emailTo, String password) {
	    try {
	        MimeMessage mensaje = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mensaje, "utf-8");

	        String html = "<!DOCTYPE html>"
	                + "<html lang='es'>"
	                + "<head>"
	                + "<meta charset='UTF-8'>"
	                + "<title>Cuenta creada</title>"
	                + "<style>"
	                + "body { margin: 0; padding: 0; background-color: #f2f2f2; font-family: 'Raleway', sans-serif; color: #0d0d0d; }"
	                + ".card { background-color: #ffffff; padding: 30px; width: 100%; max-width: 500px; margin: 40px auto; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1); border-radius: 12px; text-align: center; }"
	                + ".logo { width: 150px; margin-bottom: 20px; }"
	                + ".title { font-family: 'Poppins', sans-serif; font-size: 22px; font-weight: bold; color: #1c4d8c; margin-bottom: 10px; }"
	                + ".subtitle { font-family: 'League Spartan', sans-serif; font-size: 16px; color: #555; margin-bottom: 20px; }"
	                + ".password-box { background-color: #e3e9f3; padding: 10px; border-radius: 6px; font-weight: bold; color: #1c4d8c; display: inline-block; margin: 10px 0; }"
	                + ".footer { font-size: 13px; color: #666; margin-top: 30px; }"
	                + "</style>"
	                + "</head>"
	                + "<body>"
	                + "<table width='100%' cellpadding='0' cellspacing='0' border='0'>"
	                + "  <tr>"
	                + "    <td align='center'>"
	                + "      <div class='card'>"
	                + "        <img src='https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8ZCctTKfGFdHZHIYBnQqcSkHUvs9khINITA&s' alt='Logo de la compañía' class='logo'>"
	                + "        <div class='title'>¡Tu cuenta ha sido creada!</div>"
	                + "        <div class='subtitle'>Integrity Solutions te da la bienvenida a la plataforma InventorySoft.</div>"
	                + "        <div class='subtitle'>https://app.inventory.integritysolutions.com.ec</div>"
	                + "        <p>Tu contraseña generada es:</p>"
	                + "        <div class='password-box'>" + password + "</div>"
	                + "        <p>Inicia sesión con tu correo y esta contraseña.</p>"
	                + "        <p class='footer'>Por seguridad, te recomendamos cambiarla después de iniciar sesión.</p>"
	                + "      </div>"
	                + "    </td>"
	                + "  </tr>"
	                + "</table>"
	                + "</body>"
	                + "</html>";

			helper.setTo(emailTo);
	        helper.setSubject("Tu cuenta ha sido creada");
	        helper.setText(html, true); // true = HTML
	        helper.setFrom(this.sender);
	        mailSender.send(mensaje);

	    } catch (MessagingException e) {
	        throw new RuntimeException("Error al enviar el mensaje de bienvenida");
	    }
	}

}
