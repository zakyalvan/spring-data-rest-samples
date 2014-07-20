package com.innovez.rest.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.innovez.rest.TestConfiguration;
import com.innovez.rest.backend.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=TestConfiguration.class)
public class TestEmployeeRestEndpoint {
	private Logger logger = LoggerFactory.getLogger(TestEmployeeRestEndpoint.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@Test
	public void createSingleEmployeeResourceTest() {
		ResponseEntity<Employee> response = restTemplate.postForEntity(
				"http://localhost:8080/employees", 
				new Employee(50, "Test Employee", "test@innovez-one.com"), 
				Employee.class);
		logger.debug(response.getStatusCode().toString());
	}
	
	@Test
	public void retrieveSingleEmployeeResourceTest() {
		ResponseEntity<Resource<Employee>> employeeResourceEntity = restTemplate.exchange("http://localhost:8080/employees/{employeeId}", 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Resource<Employee>>() {},
				1);
		logger.debug(employeeResourceEntity.getBody().toString());
		logger.debug(employeeResourceEntity.getBody().getContent().toString());
	}
	
	@Test
	public void otherRetrieveSingleEmployeeResourceTest() {
		ResponseEntity<Employee> employeEntity = restTemplate.getForEntity("http://localhost:8080/employees/{employeeId}",
				Employee.class, 
				1);
		logger.debug(employeEntity.toString());
		logger.debug(employeEntity.getBody().toString());
	}
	
	@Test
	public void lastRetrieveSingleEmployeeResourceTest() {
		Employee employe = restTemplate.getForObject("http://localhost:8080/employees/{employeeId}",
				Employee.class, 
				1);
		logger.debug(employe.toString());
	}
	
	@Test
	public void retrievePagedEmployeeResourcesTest() {
		ResponseEntity<PagedResources<Employee>> employeeResourcesEntity = restTemplate.exchange(
				"http://localhost:8080/employees", 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<PagedResources<Employee>>() {});
		PagedResources<Employee> pagedEmployees = employeeResourcesEntity.getBody();
		logger.debug(pagedEmployees.toString());
		logger.debug(pagedEmployees.getContent().toString());
	}
}
