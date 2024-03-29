package com.cg.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tasktracker.entity.AdminEntity;
import com.cg.tasktracker.exceptions.AuthenticationExceptions;
import com.cg.tasktracker.exceptions.CustomException;
import com.cg.tasktracker.model.LoginModel;
import com.cg.tasktracker.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@PostMapping(value="/login")
	public ResponseEntity<AdminEntity> login(@RequestBody LoginModel credentials) throws AuthenticationExceptions{
		 AdminEntity response = service.adminLogin(credentials);
		 if(response==null)
			 throw new AuthenticationExceptions("Invalid username or password");
			 
	        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<AdminEntity> signup(@RequestBody AdminEntity signupRequest) throws CustomException{
		AdminEntity response=service.adminSignup(signupRequest);
		 if(response==null)
			 throw new CustomException("Invalid username or password");
			 
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
}
