package com.isc.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailServiceConfig {
	
	@Value("${email.username}")
	private String username;
	
	@Value("${email.password}")
	private String password;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.hostinger.com");
        mailSender.setPort(465);
        mailSender.setUsername(username); // usa tus valores reales
        mailSender.setPassword(password);
        
        mailSender.setDefaultEncoding("UTF-8");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.starttls.required", "false");
        props.put("mail.smtp.connectiontimeout", "5000"); // opcional: timeout de conexión
        props.put("mail.smtp.timeout", "5000");           // opcional: timeout de respuesta
        props.put("mail.smtp.writetimeout", "5000");      // opcional: timeout de escritura
        props.put("mail.debug", "true");                  // útil para desarrollo
        
        return mailSender;
    }
}
