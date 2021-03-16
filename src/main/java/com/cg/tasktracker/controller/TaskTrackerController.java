package com.cg.tasktracker.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.tasktracker.entity.EmployeeEntity;
import com.cg.tasktracker.entity.TaskTracker;
import com.cg.tasktracker.exceptions.CustomException;
import com.cg.tasktracker.service.TaskTrackerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/tasktracker")
public class TaskTrackerController {

	@Autowired
	private TaskTrackerService service;
	
	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskTracker> addTask(@RequestBody TaskTracker task)throws CustomException{
		
		 if(service.isTaskAlreadyRunning(task))
			 throw new CustomException("Task already running");	
		 TaskTracker response=service.addTask(task);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PutMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TaskTracker> endTask(@RequestBody TaskTracker task) throws CustomException{
		TaskTracker response=service.endTask(task);
		 if(response==null)
			 throw new CustomException("error while ending task");
			 
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	} 
	
	@PostMapping(path = "/fetch-tasks-by-date/",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskTracker>> fetchTasksByDate(@RequestBody Date date) throws CustomException {
		System.out.println("called"+ date);
		List<TaskTracker> task = service.getTasksByDate(date);
		if (task==null)
			throw new CustomException("no task present");

		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/search-emp-tasks-by-date/{empid}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TaskTracker>> fetchEmpTasksByDate(@PathVariable String empid, @RequestBody Date date) {
		List<TaskTracker> task = service.searchEmpTasks(empid, date);
		if (task==null)
			throw new CustomException("employee has not performed any task on date : " + date);

		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/fetch-tasks-by-name/{taskName}")
	public ResponseEntity<List<TaskTracker>> fetchTasksByName(@PathVariable String taskName) throws CustomException {
		List<TaskTracker> task = service.getTasksByName(taskName);
		if (task==null)
			throw new CustomException("no task present");

		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value = "/fetch-by-name-and-date/{taskname}/{date}")
	public ResponseEntity<List<TaskTracker>> searchTaskNamebyDate(@PathVariable String taskname,
			@PathVariable String date) {
		Date convertedDate =null;
		try {
			convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<TaskTracker> task = service.searchTaskNamebyDate(taskname, convertedDate);
		if (task==null)
			throw new CustomException("no task by name " + taskname + " on date : " + date);

		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
		
	}

	
	@GetMapping(value = "/fetch-by-empid-and-name/{empid}/{taskname}")
	public ResponseEntity<List<TaskTracker>> fetchByEmpIdAndName(@PathVariable String empid,
			@PathVariable String taskname) {
		System.out.println("called");
		List<TaskTracker> task = service.searchbyEmpIDTaskName(empid, taskname);
		if (task==null)
			throw new CustomException("Employee :" + empid + " has no task by name " + taskname);

		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping(value = "/fetch-by-empid-name-and-date/{empid}/{date}/{taskname}")
	public ResponseEntity<List<TaskTracker>> searchbyEmpIdTaskNameDate(@PathVariable String empid,
			@PathVariable String date, @PathVariable String taskname) {
		
		Date convertedDate =null;
		try {
			convertedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<TaskTracker> task = service.searchbyEmpIdTaskNameDate(empid, taskname, convertedDate);
		if (task==null)
			throw new CustomException("Employee :" + empid + " has no task by name " + taskname + " on date : " + date);

		return new ResponseEntity<>(task, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(value="/set-endtime")
    public void setEndTime(){
        service.setEndTime();
        }
	

	@GetMapping(value = "/getbadtask/{startdate}/{enddate}")
	public Map<TaskTracker, String> getBadTask(@PathVariable String startdate,@PathVariable String enddate) {
		Date convertedDates = null;
		Date convertedDatee = null;
		try {
			convertedDates = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			convertedDatee = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return service.badtask(convertedDates,convertedDatee);
	}

	@GetMapping(value = "/getbademployee")
	public Map<EmployeeEntity, String> getBadEmployee() {
		return service.badEmployee();
	}
	
	@GetMapping(value="/getbademployeewithdate/{startdate}/{enddate}")
	public Map<EmployeeEntity, String> getBadEmployeeWithDate(@PathVariable String startdate,@PathVariable String enddate) {
		Date convertedDates = null;
		Date convertedDatee = null;
		try {
			convertedDates = new SimpleDateFormat("yyyy-MM-dd").parse(startdate);
			convertedDatee = new SimpleDateFormat("yyyy-MM-dd").parse(enddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return service.badEmployeeAccToDate(convertedDates, convertedDatee);
	}

	
	
}
