package com.isc.api.config;

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

        mailSender.setHost("smtp.office365.com");
        mailSender.setPort(587);
        mailSender.setUsername(username); // usa tus valores reales
        mailSender.setPassword(password);
        
        mailSender.setDefaultEncoding("UTF-8");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.connectiontimeout", "10000"); // opcional: timeout de conexión
        props.put("mail.smtp.timeout", "10000");           // opcional: timeout de respuesta
        props.put("mail.smtp.writetimeout", "10000");      // opcional: timeout de escritura
        props.put("mail.debug", "true");                  // útil para desarrollo
        
        return mailSender;
    }
}
