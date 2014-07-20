package com.innovez.rest.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.innovez.rest.TestConfiguration;
import com.innovez.rest.backend.entity.Department;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfiguration.class})
public class TestDepartmentRestEndpoint {
	private Logger logger = LoggerFactory.getLogger(TestDepartmentRestEndpoint.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void retrieveNonPageableDepartmentResourcesTest() {
		ResponseEntity<Resources<Department>> responseEntity = restTemplate.exchange(
				"http://localhost:8080/departments", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<Resources<Department>>() {});
		logger.debug(responseEntity.toString());
		logger.debug(responseEntity.getBody().toString());
		logger.debug(responseEntity.getBody().getContent().toString());
	}
}
