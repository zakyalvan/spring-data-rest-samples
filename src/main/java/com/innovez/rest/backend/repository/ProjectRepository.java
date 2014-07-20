package com.innovez.rest.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innovez.rest.backend.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
