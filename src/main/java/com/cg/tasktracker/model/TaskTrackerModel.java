package com.cg.tasktracker.model;

import java.util.Date;

import com.cg.tasktracker.entity.EmployeeEntity;

public class TaskTrackerModel {

	private Long taskId;
	private EmployeeEntity empId;
	private Date taskDate;
	private String taskName;
	private String additionalDetails;
	private Date startTime;
	private Date endTime;

	public TaskTrackerModel(Long taskId, EmployeeEntity empId, Date taskDate, String taskName, String additionalDetails,
			Date startTime, Date endTime) {
		super();
		this.taskId = taskId;
		this.empId = empId;
		this.taskDate = taskDate;
		this.taskName = taskName;
		this.additionalDetails = additionalDetails;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public EmployeeEntity getEmpId() {
		return empId;
	}

	public void setEmpId(EmployeeEntity empId) {
		this.empId = empId;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAdditionalDetails() {
		return additionalDetails;
	}

	public void setAdditionalDetails(String additionalDetails) {
		this.additionalDetails = additionalDetails;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
