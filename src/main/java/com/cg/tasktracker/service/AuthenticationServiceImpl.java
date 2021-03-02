package com.cg.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.AdminRepository;
import com.cg.tasktracker.dao.EmployeeRepository;
import com.cg.tasktracker.entity.AdminEntity;
import com.cg.tasktracker.entity.EmployeeEntity;
import com.cg.tasktracker.model.LoginModel;
import com.cg.tasktracker.model.LoginResponse;
import com.cg.tasktracker.model.SignupModel;

@Service
public class AuthenticationServiceImpl{

	
	@Autowired 
	AdminRepository adminDao;
	
	@Autowired
	EmployeeRepository employeeDao;
	
	
//	public UserEntity createNewUser(SignupModel signupModel) {
//		UserEntity newUser = new UserEntity();
//		newUser.setEmail(signupModel.getEmail());
//		newUser.setName(signupModel.getName());
//		newUser.setPassword(signupModel.getPassword());
//		newUser.setRole(signupModel.getRole());
//		
//		return newUser;
//	}
	
	
	public AdminEntity createNewAdmin(SignupModel signupModel) {
		AdminEntity newUser = new AdminEntity();
		newUser.setAdminId(signupModel.getUserId());
		newUser.setEmail(signupModel.getEmail());
		newUser.setName(signupModel.getName());
		newUser.setPassword(signupModel.getPassword());
		newUser.setRole(signupModel.getRole());
		return newUser;
	}
	
	public EmployeeEntity createNewEmployee(SignupModel signupModel) {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setEmpId(signupModel.getUserId());
		employee.setEmail(signupModel.getEmail());
		employee.setName(signupModel.getName());
		employee.setPassword(signupModel.getPassword());
		employee.setRole(signupModel.getRole());
		employee.setBuJoining(signupModel.getBuJoining());
		employee.setPhoneNumber(signupModel.getPhoneNumber());
		employee.setGender(signupModel.getGender());
		return employee;
	}
	
	
	private LoginResponse createLoginResponse(SignupModel signupModel) {
		LoginResponse response = new LoginResponse();
		response.setEmail(signupModel.getEmail());
		response.setName(signupModel.getName());
		response.setUserId(signupModel.getUserId());
		response.setRole(signupModel.getRole());
		return response;
	}
	
	public LoginResponse signup(SignupModel signupModel) {
		
		if(signupModel.getRole().equals("ADMIN")) {
			if(adminDao.existsByEmail(signupModel.getEmail())) {
				return null;
			}
			else {
				AdminEntity newAdmin = createNewAdmin(signupModel);
				adminDao.save(newAdmin);
			}
		}else if(signupModel.getRole().equalsIgnoreCase("EMPLOYEE")) {
			if(employeeDao.existsByEmail(signupModel.getEmail())) {
				return null;
			}
			else {
				EmployeeEntity newEmployee = createNewEmployee(signupModel);
				employeeDao.save(newEmployee);
			}
		}
		else return null;
		
		return createLoginResponse(signupModel);
		
	}

	public LoginResponse login(LoginModel credentials) {

		LoginResponse response = new LoginResponse();
		if(credentials.getRole().equals("ADMIN")) {
			AdminEntity admin = adminDao.findByEmail(credentials.getEmail());
			if(admin.getPassword().equals(credentials.getPassword())) {
				response.setName(admin.getName());
				response.setUserId(admin.getAdminId());
				response.setEmail(credentials.getEmail());
				response.setRole(credentials.getRole());
			}
			else return null;
		}
		else if(credentials.getRole().equals("EMPLOYEE")) {
			EmployeeEntity emp = employeeDao.findByEmail(credentials.getEmail());
			if(emp.getPassword().equals(credentials.getPassword())) {
				response.setName(emp.getName());
				response.setUserId(emp.getEmpId());
				response.setEmail(credentials.getEmail());
				response.setRole(credentials.getRole());
			}
			else return null;
		}
		
		return null;
		
	}

}
