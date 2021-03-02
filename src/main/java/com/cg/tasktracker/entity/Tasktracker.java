package com.cg.tasktracker.entity;

 

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

 

@Entity
public class Tasktracker {

 

    @Id
    @Column(name="task_tracker_id")
    private int taskId;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "emp_id")
    private EmployeeEntity empId;

    @Column(name = "task_date", nullable = false)
    private Date taskDate;
    
    @Column(name = "task_name", nullable = false)
    private String taskName;
    
    @Column(name = "additional_detail", nullable = false, length = 6000)
    private String additionalDetails;
    
    @Column(name = "start_time", nullable = false)
    private Date startTime;
    
    @Column(name = "end_time", nullable = false)
    private Date endTime;


    public Tasktracker() {
        super();
    }

    public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	@JsonIgnore
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