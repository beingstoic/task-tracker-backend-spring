package com.cg.tasktracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmployeeEntity {
	@Id
	@Column(name="emp_id")
	private String empId;

	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@Column(name="email",unique=true, nullable=false)
	private String email;
	
	@Column(name="role", nullable=false)
	private String role;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="bu_joining")
	private String buJoining;


	public String getEmpId() {
		return empId;
	}


	public void setEmpId(String empId) {
		this.empId = empId;
	}


	public EmployeeEntity() {
		super();
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



	@Override
	public String toString() {
		return "EmployeeEntity [empId=" + empId + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", role=" + role + ", gender=" + gender + ", phoneNumber=" + phoneNumber + ", buJoining=" + buJoining
				+ "]";
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
