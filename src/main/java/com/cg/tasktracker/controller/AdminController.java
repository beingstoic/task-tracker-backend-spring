package com.cg.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tasktracker.entity.AdminEntity;
import com.cg.tasktracker.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@GetMapping("/login/{name}/{password}")
	public AdminEntity login(@PathVariable String name,@PathVariable String password) {
		return this.service.login(name, password);
	}
	
}
