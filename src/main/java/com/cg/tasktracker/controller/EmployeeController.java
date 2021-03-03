package com.cg.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tasktracker.exceptions.CustomException;
import com.cg.tasktracker.model.EmployeeModel;
import com.cg.tasktracker.model.LoginModel;
import com.cg.tasktracker.service.EmployeeServiceImpl;

@RestController
@RequestMapping("/employee")
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl service;

	@PostMapping(value="/login")
	public ResponseEntity<EmployeeModel> login(@RequestBody LoginModel credentials) throws CustomException{
		 EmployeeModel response = service.employeeLogin(credentials);
		 if(response==null)
			 throw new CustomException("Invalid username or password");
			 
	        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<EmployeeModel> signup(@RequestBody EmployeeModel signupRequest) throws CustomException{
		EmployeeModel response=service.employeeSignup(signupRequest);
		 if(response==null)
			 throw new CustomException("Invalid username or password");
			 
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
}
