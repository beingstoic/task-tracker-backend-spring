package com.cg.tasktracker.service;

import com.cg.tasktracker.entity.AdminEntity;

public interface AdminService {

	AdminEntity login(String name, String password);

}
