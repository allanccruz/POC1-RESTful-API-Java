package com.github.allanccruz.POC1RESTfulAPI.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    ModelMapper mapper() { return new ModelMapper(); }

}
