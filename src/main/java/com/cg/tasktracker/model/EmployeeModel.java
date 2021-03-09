package com.cg.tasktracker.model;

import java.util.List;

import com.cg.tasktracker.entity.TaskTracker;

public class EmployeeModel {

	private String empId;
	private List<TaskTracker> tasks;
	private String name;
	private String password;
	private String email;
	private String role;
	private String gender;
	private String phoneNumber;
	private String buJoining;

	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public List<TaskTracker> getTasks() {
		return tasks;
	}
	public void setTasks(List<TaskTracker> tasks) {
		this.tasks = tasks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBuJoining() {
		return buJoining;
	}
	public void setBuJoining(String buJoining) {
		this.buJoining = buJoining;
	}
	
	
}
