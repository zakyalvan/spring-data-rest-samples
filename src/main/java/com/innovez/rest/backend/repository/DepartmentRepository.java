package com.innovez.rest.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovez.rest.backend.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
