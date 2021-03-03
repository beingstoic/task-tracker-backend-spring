package com.cg.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.AdminRepository;
import com.cg.tasktracker.entity.AdminEntity;
import com.cg.tasktracker.model.AdminModel;
import com.cg.tasktracker.model.LoginModel;

@Service
public class AdminService{

	@Autowired
	AdminRepository adminDao;
	
	
	public AdminEntity convert(AdminModel adminModel) {
		AdminEntity employee = new AdminEntity();
		employee.setAdminId(adminModel.getAdminId());
		employee.setEmail(adminModel.getEmail());
		employee.setName(adminModel.getName());
		employee.setPassword(adminModel.getPassword());
		employee.setRole(adminModel.getRole());
		return employee;
	}
	
	
	public AdminModel convert(AdminEntity adminModel) {
		AdminModel employee = new AdminModel();
		employee.setAdminId(adminModel.getAdminId());
		employee.setEmail(adminModel.getEmail());
		employee.setName(adminModel.getName());
		employee.setPassword(adminModel.getPassword());
		employee.setRole(adminModel.getRole());
		return employee;
	}

	public AdminModel adminSignup(AdminModel adminModel) {
		if(adminDao.existsByEmail(adminModel.getEmail())) {
			return null;
		}
		adminDao.save(convert(adminModel));
		
		return adminModel;
		
	}

	public AdminModel adminLogin(LoginModel credentials) {
		AdminEntity emp = adminDao.findByEmail(credentials.getEmail());
		if(emp.getPassword().equals(credentials.getPassword())) {
			return convert(emp);
		}
		return null;
	}
}
