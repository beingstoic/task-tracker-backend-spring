package com.cg.tasktracker.service;

import com.cg.tasktracker.entity.UserEntity;
import com.cg.tasktracker.model.LoginModel;
import com.cg.tasktracker.model.LoginResponse;
import com.cg.tasktracker.model.SignupModel;

public interface UserService {
	
	public LoginResponse signup(SignupModel signupModel);

	public LoginResponse login(LoginModel credentials);
}
