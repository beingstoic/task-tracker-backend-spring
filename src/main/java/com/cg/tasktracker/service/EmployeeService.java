package com.cg.tasktracker.service;

import com.cg.tasktracker.entity.EmployeeEntity;

public interface EmployeeService {

	EmployeeEntity login(String email, String pass);

}
