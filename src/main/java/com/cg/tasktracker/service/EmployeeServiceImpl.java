package com.cg.tasktracker.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.EmployeeRepository;
import com.cg.tasktracker.dao.TaskTrackerRepository;
import com.cg.tasktracker.entity.EmployeeEntity;
import com.cg.tasktracker.entity.TaskTracker;
import com.cg.tasktracker.model.LoginModel;

@Service
public class EmployeeServiceImpl  {

	@Autowired
	EmployeeRepository employeeDao;
	
	@Autowired
	TaskTrackerRepository taskRepo;


	public EmployeeEntity employeeSignup(EmployeeEntity employeeModel) {
		if(employeeDao.existsByEmail(employeeModel.getEmail())) {
			return null;
		}
		employeeDao.save(employeeModel);
		
		return employeeModel;
		
	}

	public EmployeeEntity employeeLogin(LoginModel credentials) {
		EmployeeEntity emp = employeeDao.findByEmail(credentials.getEmail());
		if(emp.getPassword().equals(credentials.getPassword())) {
			return emp;
		}
		return null;
	}
	
	public List<TaskTracker> fetchTasks(String empId){
		return taskRepo.fetchAllByEmpId(empId);
	}
	
	public EmployeeEntity fetchEmployee(String empId){
		return employeeDao.findByEmpId(empId);
	}
	
	public List<TaskTracker> fetchEmployeeTasks(String empId) {
		return taskRepo.fetchAllByEmpId(empId);
	}
}
