package com.innovez.rest.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovez.rest.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
