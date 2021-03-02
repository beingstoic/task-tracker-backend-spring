package com.cg.tasktracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.AdminRepository;
import com.cg.tasktracker.entity.AdminEntity;

@Service
public class AdminService{

	@Autowired
	private AdminRepository repo;
	
	
	public AdminEntity login(String name,String password) {
		for(AdminEntity a: repo.findAll()) {
			//if((a.getName().equalsIgnoreCase(name))&&a.getPassword().equals(password))
				return a;
		}
		return null;
		
	}
}
