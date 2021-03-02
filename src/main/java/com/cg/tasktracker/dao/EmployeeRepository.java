package com.cg.tasktracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.tasktracker.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
	boolean existsByEmail(String email);
	EmployeeEntity findByEmail(String email);
}
