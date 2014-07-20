package com.innovez.rest.test;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
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
	public void createAndDeleteDepartmentObjectTest() {
		Department department = new Department();
		department.setName("Test Department Name");
		
		URI newDepartmentUri = restTemplate.postForLocation("http://localhost:8080/departments", department);
		logger.debug("New created department resource URI : " + newDepartmentUri.toString());
		
		logger.debug("Retrieving new created department resorce");
		ResponseEntity<Department> responseEntity = restTemplate.getForEntity(newDepartmentUri, Department.class);
		logger.debug("New created department retrieved : " + responseEntity.getBody());
		
		logger.debug("Deleting new created department resource");
		restTemplate.delete(newDepartmentUri);
	}
	
	@Test
	public void retrieveDepartmentResourceTest() {
		ResponseEntity<Resource<Department>> responseEntity = restTemplate.exchange(
				"http://localhost:8080/departments/{departmentId}", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<Resource<Department>>() {}, 
				1);
		
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.debug(responseEntity.toString());
			logger.debug(responseEntity.getBody().toString());
			logger.debug(responseEntity.getBody().getContent().toString());
		}
		else {
			logger.error("Something wrong while request department resource");
		}
	}
	
	@Test
	public void retrieveDepartmentObjectTest() {
		ResponseEntity<Department> responseEntity = restTemplate.getForEntity(
				"http://localhost:8080/departments/{id}", 
				Department.class, 
				2);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.debug(responseEntity.toString());
			logger.debug(responseEntity.getBody().toString());
		}
		else {
			logger.error("Something wrong while request deparment object");
		}
	}
	
	@Test
	public void otherRetrieveDepartmentObjectTest() {
		Department department = restTemplate.getForObject(
				"http://localhost:8080/departments/{id}", 
				Department.class, 
				2);
		Assert.isTrue(department != null);
		logger.debug(department.toString());
	}
	
	@Test
	public void retrieveNonPageableDepartmentResourcesTest() {
		ResponseEntity<Resources<Department>> responseEntity = restTemplate.exchange(
				"http://localhost:8080/departments", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<Resources<Department>>() {});
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			logger.debug(responseEntity.toString());
			logger.debug(responseEntity.getBody().toString());
			logger.debug(responseEntity.getBody().getContent().toString());
		}
		else {
			logger.error("Something wrong while request department resources");
		}
	}
}
