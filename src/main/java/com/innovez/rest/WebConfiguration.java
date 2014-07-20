package com.innovez.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@Import(RepositoryRestMvcConfiguration.class)
@ComponentScan(basePackages="com.innovez.rest.web")
public class WebConfiguration extends WebMvcConfigurerAdapter {

}
