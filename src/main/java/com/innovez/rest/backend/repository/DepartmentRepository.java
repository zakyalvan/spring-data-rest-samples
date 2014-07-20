package com.innovez.rest.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.innovez.rest.backend.entity.Department;

/**
 * Department repository. Using non-pageable repository.
 * 
 * @author zakyalvan
 */
public interface DepartmentRepository extends CrudRepository<Department, Integer> {

}
