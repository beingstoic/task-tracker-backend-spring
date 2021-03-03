package com.cg.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.EmployeeRepository;
import com.cg.tasktracker.entity.EmployeeEntity;
import com.cg.tasktracker.model.EmployeeModel;
import com.cg.tasktracker.model.LoginModel;

@Service
public class EmployeeServiceImpl  {

	@Autowired
	EmployeeRepository employeeDao;
	
	
	public EmployeeEntity convert(EmployeeModel employeeModel) {
		EmployeeEntity employee = new EmployeeEntity();
		employee.setEmpId(employeeModel.getEmpId());
		employee.setEmail(employeeModel.getEmail());
		employee.setName(employeeModel.getName());
		employee.setPassword(employeeModel.getPassword());
		employee.setRole(employeeModel.getRole());
		employee.setBuJoining(employeeModel.getBuJoining());
		employee.setPhoneNumber(employeeModel.getPhoneNumber());
		employee.setGender(employeeModel.getGender());
		return employee;
	}
	
	
	public EmployeeModel convert(EmployeeEntity employeeModel) {
		EmployeeModel employee = new EmployeeModel();
		employee.setEmpId(employeeModel.getEmpId());
		employee.setEmail(employeeModel.getEmail());
		employee.setName(employeeModel.getName());
		employee.setPassword(employeeModel.getPassword());
		employee.setRole(employeeModel.getRole());
		employee.setBuJoining(employeeModel.getBuJoining());
		employee.setPhoneNumber(employeeModel.getPhoneNumber());
		employee.setGender(employeeModel.getGender());
		return employee;
	}

	public EmployeeModel employeeSignup(EmployeeModel employeeModel) {
		if(employeeDao.existsByEmail(employeeModel.getEmail())) {
			return null;
		}
		employeeDao.save(convert(employeeModel));
		
		return employeeModel;
		
	}

	public EmployeeModel employeeLogin(LoginModel credentials) {
		EmployeeEntity emp = employeeDao.findByEmail(credentials.getEmail());
		if(emp.getPassword().equals(credentials.getPassword())) {
			return convert(emp);
		}
		return null;
	}
}
