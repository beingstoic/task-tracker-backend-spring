package com.cg.tasktracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="emp_id")
	private long empId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usr_id")
	private UserEntity user;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="bu_joining")
	private String buJoining;



	public long getEmpId() {
		return empId;
	}







	public void setEmpId(long empId) {
		this.empId = empId;
	}







	public EmployeeEntity() {
		super();
	}

	





	public UserEntity getUser() {
		return user;
	}



	public void setUser(UserEntity user) {
		this.user = user;
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
