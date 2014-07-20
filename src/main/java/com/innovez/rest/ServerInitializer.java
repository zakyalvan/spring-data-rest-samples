package com.innovez.rest;

import javax.servlet.Filter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServerInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {BackendConfiguration.class};
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfiguration.class};
	}
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {new OpenEntityManagerInViewFilter()};
	}
}
