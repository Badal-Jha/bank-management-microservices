package com.badal.customerservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
