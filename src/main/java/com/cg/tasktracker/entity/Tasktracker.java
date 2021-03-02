package com.cg.tasktracker.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tasktracker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tt_id;
	
	
	@ManyToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "em_id")
	private EmployeeEntity emp;
	
	
	private Date taskdate;
	private String taskname;
	private String additional_detail;
	private Date start_time;
	private Date end_time;

	public Tasktracker() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getTt_id() {
		return tt_id;
	}

	public void setTt_id(int tt_id) {
		this.tt_id = tt_id;
	}





	public EmployeeEntity getEmp() {
		return emp;
	}


	public void setEmp(EmployeeEntity emp) {
		this.emp = emp;
	}


	public Date getTaskdate() {
		return taskdate;
	}

	public void setTaskdate(Date taskdate) {
		this.taskdate = taskdate;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getAdditional_detail() {
		return additional_detail;
	}

	public void setAdditional_detail(String additional_detail) {
		this.additional_detail = additional_detail;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

}
