package com.cg.tasktracker.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.TaskTrackerRepository;
import com.cg.tasktracker.entity.TaskTracker;
import com.cg.tasktracker.exceptions.CustomException;

@Service
public class TaskTrackerService {

	@Autowired
	TaskTrackerRepository repo;


	public TaskTracker addTask(TaskTracker tasktracker) {
		
		tasktracker.setTaskDate(new java.sql.Date(System.currentTimeMillis()));
		tasktracker.setStartTime(new Timestamp(System.currentTimeMillis()));
		return repo.save(tasktracker);
	}

	public TaskTracker endTask(TaskTracker task) {
		if (repo.existsByTaskId(task.getTaskId())) {
			
			task = repo.getAssignedTask(task.getTaskId(), task.getEmployee().getEmpId());
			
			if(task==null) {
				  throw new CustomException("Task is not assigned to particular user");
			  }
			else if(task.getDuration()!=0) {
					throw new CustomException("Task already ended");
				 }
		  
			task.setEndTime(new Timestamp(System.currentTimeMillis()));
			task.setDuration(task.getEndTime().getTime()-task.getStartTime().getTime());

			return repo.save(task);
		}
			throw new CustomException("Task not found");
	}
	
	public boolean isTaskAlreadyRunning(TaskTracker task) {
		TaskTracker taskk = repo.checkRunningTask(task.getEmployee().getEmpId());
		System.out.println(taskk);
		if(taskk!=null)
			return true;
		return false;
	}
}
