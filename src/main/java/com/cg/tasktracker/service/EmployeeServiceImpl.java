package com.cg.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.EmployeeRepository;
import com.cg.tasktracker.entity.EmployeeEntity;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	@Override
	public EmployeeEntity login(String email,String pass) {
		for(EmployeeEntity e:repo.findAll()) {
		//	if((e.getEmail().equals(email))&&(e.getPassword().equals(pass)))
				return e;
		}
		return null;
	}
	
}
