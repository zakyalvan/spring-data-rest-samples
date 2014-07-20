package com.innovez.rest;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableSpringConfigured
public class TestConfiguration {
	@Bean
	public RestTemplate restTemplate() {
		/**
		 * Customize http message converter so that we can resolve _embedded (HAL specific) property to expected type. 
		 */
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.registerModule(new Jackson2HalModule());
		
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		converter.setObjectMapper(objectMapper);
		
		return new RestTemplate(Collections.<HttpMessageConverter<?>>singletonList(converter));
	}
}
