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
import com.cg.tasktracker.model.LoginModel;
import com.cg.tasktracker.model.LoginResponse;
import com.cg.tasktracker.model.SignupModel;
import com.cg.tasktracker.service.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	AuthenticationServiceImpl authService;

	@PostMapping(value="/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginModel credentials) throws CustomException{
		 LoginResponse response = authService.login(credentials);
		 if(response==null)
			 throw new CustomException("Invalid username or password");
			 
	        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(value="/register")
	public ResponseEntity<LoginResponse> signup(@RequestBody SignupModel signupRequest) throws CustomException{
		LoginResponse response=authService.signup(signupRequest);
		 if(response==null)
			 throw new CustomException("Invalid username or password");
			 
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	
	
}
