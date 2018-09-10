package com.overseaslabs.examples.web;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, APIResponseHandler apiResponseHandler) {
        return builder
                .errorHandler(apiResponseHandler)
                .build();
    }

    @Bean
    public NetObjectsFactory netObjectsFactory() {
        return new NetObjectsFactory();
    }
}
