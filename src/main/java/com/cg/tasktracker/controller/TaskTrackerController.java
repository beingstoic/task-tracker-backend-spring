package com.cg.tasktracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tasktracker.entity.TaskTracker;
import com.cg.tasktracker.exceptions.CustomException;
import com.cg.tasktracker.service.TaskTrackerService;

@RestController
@CrossOrigin("*")
public class TaskTrackerController {

	@Autowired
	private TaskTrackerService service;
	
	@PostMapping(path = "tasktracker", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskTracker> addTask(@RequestBody TaskTracker task)throws CustomException{
		
		 if(service.isTaskAlreadyRunning(task))
			 throw new CustomException("Task already running");	
		 TaskTracker response=service.addTask(task);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path="/tasktracker", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskTracker> endTask(@RequestBody TaskTracker task) throws CustomException{
		TaskTracker response=service.endTask(task);
		 if(response==null)
			 throw new CustomException("error while ending task");
			 
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	} 
	
	
}
