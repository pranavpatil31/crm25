package com.crm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();//we are doing dependency injection on external liibraries
    }
}
