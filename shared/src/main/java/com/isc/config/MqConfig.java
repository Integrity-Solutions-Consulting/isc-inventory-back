// package com.bolivariano.microservice.tuklajem.config;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.ibm.mq.jakarta.jms.MQConnectionFactory;
// import com.ibm.mq.jakarta.jms.MQQueueConnectionFactory;
// import com.ibm.msg.client.jakarta.jms.JmsConstants;
// import com.ibm.msg.client.jakarta.wmq.common.CommonConstants;

// import jakarta.jms.JMSException;
// import lombok.Data;

// @Data
// @Configuration
// @ConfigurationProperties(prefix = "mq.config")
// public class MqConfig {

//     private String host;
//     private String channel;
//     private String queueManager;
//     private String requestQueue;
//     private String responseQueue;
//     private String user;
//     private String password;
//     private Integer port;

//     @Bean
//     MQConnectionFactory mqConnectionFactory() throws JMSException {
//         MQQueueConnectionFactory factory = new MQQueueConnectionFactory();
//         factory.setChannel(this.channel);
//         factory.setHostName(this.host);
//         factory.setPort(this.port);
//         factory.setQueueManager(this.queueManager);
//         factory.setTransportType(JmsConstants.ADMIN_QUEUE_DOMAIN); // Tipo de transporte TCP/IP
//         factory.setIntProperty(CommonConstants.WMQ_CLIENT_RECONNECT_OPTIONS, CommonConstants.WMQ_CLIENT_RECONNECT);
//         factory.setStringProperty(JmsConstants.USERID, this.user);
//         factory.setStringProperty(JmsConstants.PASSWORD, this.password);
//         return factory;
//     }
// }
// * por si algun dia necesitamos comunicacion mediante message broker