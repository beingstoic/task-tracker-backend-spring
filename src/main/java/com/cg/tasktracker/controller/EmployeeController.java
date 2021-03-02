package com.cg.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tasktracker.entity.EmployeeEntity;
import com.cg.tasktracker.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping("/login/{email}/{password}")
	public EmployeeEntity login(@PathVariable String email,@PathVariable String password) {
		return this.service.login(email, password);
	}
	
}
