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
import com.innovez.rest.backend.entity.Project;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestConfiguration.class})
public class TestProjectRestEndpoint {
	private Logger logger = LoggerFactory.getLogger(TestProjectRestEndpoint.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void retrieveProjectResourcesTest() {
		logger.debug("====== Start retrieveProjectResourcesTest()");
		ResponseEntity<PagedResources<Project>> projectResourceResponseEntity = restTemplate.exchange(
				"http://localhost:8080/projects", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<PagedResources<Project>>() {},
				1);
		logger.debug(projectResourceResponseEntity.toString());
		logger.debug(projectResourceResponseEntity.getBody().toString());
		logger.debug(projectResourceResponseEntity.getBody().getContent().toString());
		logger.debug("====== End retrieveProjectResourcesTest()");
	}
	
	@Test
	public void retrieveProjectResourceTest() {
		logger.debug("====== Start retrieveProjectResourceTest()");
		ResponseEntity<Resource<Project>> projectResourceResponseEntity = restTemplate.exchange(
				"http://localhost:8080/projects/{projectId}", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<Resource<Project>>() {},
				1);
		logger.debug(projectResourceResponseEntity.toString());
		logger.debug(projectResourceResponseEntity.getBody().toString());
		logger.debug(projectResourceResponseEntity.getBody().getContent().toString());
		logger.debug("====== End retrieveProjectResourcesTest()");
	}
	
	@Test
	public void retrieveProjectObjectTest() {
		logger.debug("====== Start retrieveProjectObjectTest()");
		Project project = restTemplate.getForObject("http://localhost:8080/projects/{projectId}", Project.class, 1);
		logger.debug(project.toString());
		logger.debug("====== End retrieveProjectObjectTest()");
	}
	
	@Test
	public void retrieveProjectManagerObjectTest() {
		logger.debug("====== Start retrieveProjectManagerObjectTest()");
		Employee manager = restTemplate.getForObject("http://localhost:8080/projects/{projectId}/manager", Employee.class, 1);
		logger.debug(manager.toString());
		logger.debug("====== End retrieveProjectManagerObjectTest()");
	}
	
	@Test
	public void retrieveProjectMemberObjectsTest() {
		logger.debug("====== Start retrieveProjectMemberObjectTest()");
		ResponseEntity<PagedResources<Employee>> employeesResourceEntity = restTemplate.exchange("http://localhost:8080/projects/{projectId}/members", 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<PagedResources<Employee>>() {}, 
				1);
		logger.debug(employeesResourceEntity.toString());
		logger.debug(employeesResourceEntity.getBody().getContent().toString());
		logger.debug("====== End retrieveProjectMemberObjectTest()");
	}
}
