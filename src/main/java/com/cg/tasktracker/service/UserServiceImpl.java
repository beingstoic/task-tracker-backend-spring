package com.cg.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.AdminRepository;
import com.cg.tasktracker.dao.EmployeeRepository;
import com.cg.tasktracker.dao.UserDao;
import com.cg.tasktracker.entity.AdminEntity;
import com.cg.tasktracker.entity.EmployeeEntity;
import com.cg.tasktracker.entity.UserEntity;
import com.cg.tasktracker.model.LoginModel;
import com.cg.tasktracker.model.LoginResponse;
import com.cg.tasktracker.model.SignupModel;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Autowired 
	AdminRepository adminDao;
	
	@Autowired
	EmployeeRepository employeeDao;
	
	
	public UserEntity createNewUser(SignupModel signupModel) {
		UserEntity newUser = new UserEntity();
		newUser.setEmail(signupModel.getEmail());
		newUser.setName(signupModel.getName());
		newUser.setPassword(signupModel.getPassword());
		newUser.setRole(signupModel.getRole());
		
		return newUser;
	}
	
	
	public AdminEntity createNewAdmin(SignupModel signupModel, UserEntity newUser) {
		AdminEntity admin = new AdminEntity();
		admin.setUser(newUser);
		return admin;
	}
	
	public EmployeeEntity createNewEmployee(SignupModel signupModel, UserEntity newUser) {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setUser(newUser);
		employee.setBuJoining(signupModel.getBuJoining());
		employee.setPhoneNumber(signupModel.getPhoneNumber());
		employee.setGender(signupModel.getGender());
		return employee;
	}
	
	
	private LoginResponse createLoginResponse(UserEntity newUser) {
		LoginResponse response = new LoginResponse();
		response.setEmail(newUser.getEmail());
		response.setName(newUser.getName());
		response.setUserId(newUser.getUserId());
		
		return response;
	}
	
	
	@Override
	public LoginResponse signup(SignupModel signupModel) {
		if(userDao.existsByEmail(signupModel.getEmail())) {
			return null;
		}
		
		UserEntity newUser = createNewUser(signupModel);
		newUser = userDao.save(newUser);
		if(newUser.getRole().equalsIgnoreCase("ADMIN")) {
			AdminEntity admin = createNewAdmin(signupModel, newUser);
			adminDao.save(admin);
		
		}else if(newUser.getRole().equalsIgnoreCase("EMPLOYEE")) {
			EmployeeEntity employee = createNewEmployee(signupModel, newUser);
			employeeDao.save(employee);
		}
		
		return createLoginResponse(newUser);
		
	}




	@Override
	public LoginResponse login(LoginModel credentials) {
		UserEntity user = userDao.validateUser(credentials.getEmail(), credentials.getPassword());
		if(user==null) {
			return null;
		}
		return createLoginResponse(user);
	
	}

}
