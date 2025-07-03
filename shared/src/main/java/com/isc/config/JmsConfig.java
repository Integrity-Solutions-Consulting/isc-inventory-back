// package com.bolivariano.microservice.tuklajem.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.jms.core.JmsTemplate;

// import com.ibm.mq.jakarta.jms.MQConnectionFactory;

// @Configuration
// public class JmsConfig {

//     @Bean
//     JmsTemplate jmsTemplate(MQConnectionFactory mqConnectionFactory) {
//         JmsTemplate jmsTemplate = new JmsTemplate();
//         jmsTemplate.setConnectionFactory(mqConnectionFactory);
//         jmsTemplate.setSessionTransacted(true);
//         return jmsTemplate;
//     }
// }
    

// * por si algun dia necesitamos comunicacion mediante message broker