package com.innovez.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Lazy loading entities properties still in problem (bug).
 * Watch the progress on https://jira.spring.io/browse/DATAREST-269
 * 
 * @author zakyalvan
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan(basePackages="com.innovez.rest.web")
public class WebRestConfiguration extends RepositoryRestMvcConfiguration {
	@Override
	protected void configureJacksonObjectMapper(ObjectMapper objectMapper) {
		objectMapper.registerModule(new Hibernate4Module());
	}
}
