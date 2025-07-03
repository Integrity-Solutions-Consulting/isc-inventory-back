package com.isc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "rest.config")
public class RestClientConfig {

    private Number readTimeout;
    private Number connectTimeout;
    private String baseUrl;

    @Bean
    RestClient restClient() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();

        clientHttpRequestFactory.setConnectTimeout(this.connectTimeout.intValue());
        clientHttpRequestFactory.setReadTimeout(this.readTimeout.intValue());

        return RestClient
                .builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .requestFactory(clientHttpRequestFactory)
                .baseUrl(this.baseUrl)
                .build();
    }
}