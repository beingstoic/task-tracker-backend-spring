package com.cg.tasktracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class AdminEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private long adminId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="us_id")
	private UserEntity user;


	public long getAdminId() {
		return adminId;
	}


	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}



}
