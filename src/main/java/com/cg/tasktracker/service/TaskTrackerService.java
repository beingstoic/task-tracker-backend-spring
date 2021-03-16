package com.cg.tasktracker.service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.EmployeeRepository;
import com.cg.tasktracker.dao.TaskTrackerRepository;
import com.cg.tasktracker.entity.EmployeeEntity;
import com.cg.tasktracker.entity.TaskTracker;
import com.cg.tasktracker.exceptions.CustomException;

@Service
public class TaskTrackerService {

	@Autowired
	TaskTrackerRepository repo;
	
	@Autowired
	EmployeeRepository empRepo;

	public TaskTracker addTask(TaskTracker tasktracker) {

		tasktracker.setTaskDate(new java.sql.Date(System.currentTimeMillis()));
		tasktracker.setStartTime(new Timestamp(System.currentTimeMillis()));
		return repo.save(tasktracker);
	}

	public TaskTracker endTask(TaskTracker task) {
		if (repo.existsByTaskId(task.getTaskId())) {

			task = repo.getAssignedTask(task.getTaskId(), task.getEmployee().getEmpId());

			if (task == null) {
				throw new CustomException("Task is not assigned to particular user");
			} else if (task.getDuration() != 0) {
				throw new CustomException("Task already ended");
			}

			task.setEndTime(new Timestamp(System.currentTimeMillis()));
			task.setDuration(task.getEndTime().getTime() - task.getStartTime().getTime());

			return repo.save(task);
		}
		throw new CustomException("Task not found");
	}

	public boolean isTaskAlreadyRunning(TaskTracker task) {
		TaskTracker taskk = repo.checkRunningTask(task.getEmployee().getEmpId());
		System.out.println(taskk);
		if (taskk != null)
			return true;
		return false;
	}

//	Created By Yash

	public List<TaskTracker> getTasksByDate(Date date) { // search tasks by date
		return repo.getTaskAccToDate(date);
	}

	public List<TaskTracker> searchEmpTasks(String empid, Date date) { // search tasks by empid and date
		return repo.searchTasks(date, empid);
	}

	public List<TaskTracker> searchTaskNamebyDate(String taskName, Date date) { // search tasks by taskname and date
		return repo.searchTaskNamebyDate(taskName, date);
	}

	public List<TaskTracker> searchbyEmpIdTaskNameDate(String empid, String taskName, Date date) { // search tasks by
																									// empid, taskname
																									// and date
		return repo.searchbyEmpIdTaskNameDate(empid, taskName, date);
	}

	public List<TaskTracker> searchbyEmpIDTaskName(String empid, String taskName) { // search tasks by empid and
																					// taskNAme
		return repo.fetchAllByEmpIdandTaskName(empid, taskName);
	}

	
	
	public List<TaskTracker> getTasksByName(String taskName) {
		return repo.findAllByTaskName(taskName);
	}

	public Map<TaskTracker, String> badtask(Date startdate, Date enddate) { // bad task if running for 4 hours and above
		Map<TaskTracker, String> badTask1 = new HashMap<TaskTracker, String>();

		for (TaskTracker task : repo.findAll()) {
			if ((task.getTaskDate().getDate()>=startdate.getDate()) && (task.getTaskDate().getDate()<=enddate.getDate())) {
				if (task.getTaskName().equalsIgnoreCase("break")) {

				} // for
				else {
					if (task.getEndTime() == null) {
						if (Double.valueOf(
								(new Timestamp(System.currentTimeMillis()).getTime() - task.getStartTime().getTime())
										/ 3600000) >= 4)
							badTask1.put(task, "task running for 4 hours and above");
					} // first if
					else {
						if (task.getDuration() > 14400000)
							badTask1.put(task, "task runnong for 4 hours and above");
					}
				} // else
			} // main if
		} // for
		return badTask1;
	}

	public void setEndTime() { // call this function on ngOnInit() which will end the tasks running more than 5
								// hrs to current time
		for (TaskTracker task : repo.findAll()) {
			if (task.getEndTime() == null
					&& ((new Timestamp(System.currentTimeMillis()).getTime() - task.getStartTime().getTime())
							/ 3600000 >= 6)) {
				task.setEndTime(new Timestamp(task.getStartTime().getTime() + 21600000));
				task.setDuration(21600000);
				repo.save(task);
			} // if
		} // for
	}

	public Map<EmployeeEntity, String> badEmployee() {
		Map<EmployeeEntity, String> badEmployee = new HashMap<EmployeeEntity, String>();
		int count = 0;
		long dur = 0;
		for (EmployeeEntity emp : empRepo.findAll()) {
			count = 0;
			dur = 0;
			for (TaskTracker task : repo.findAll()) {
				if (task.getTaskDate().getDate() == (Date.from(Instant.now().minus(Duration.ofDays(1)))).getDate()) {
					System.out.println("Date is today");
					if (task.getTaskName().equals("Break")) { // break as a task is not counted
						System.out.println("break is counted");
						break;
					} // if
					else {
						if (task.getEmployee().getEmpId().equals(emp.getEmpId())) {
							System.out.println("there is a task with an employee today");
							count += 1;
							dur += task.getDuration();
						} // if

					} // else
				} // main if
			} // for
			if (count == 0)
				badEmployee.put(emp, "No tasks done yesterday ");
			else if (count == 1)
				badEmployee.put(emp, "Only 1 task done yesterday with duration = " + dur / 1000 + " sec");
			else if (dur < 7200000)
				badEmployee.put(emp, "total task duration is less than 2 hours. duration = " + dur / 1000 + " sec");

		} // for
		return badEmployee;
	}
	
	
	public Map<EmployeeEntity, String> badEmployeeAccToDate(Date startDate,Date endDate) {
		Map<EmployeeEntity, String> badEmployee = new HashMap<EmployeeEntity, String>();
		int count = 0;
		long dur = 0;
		for (EmployeeEntity emp : empRepo.findAll()) {
			count = 0;
			dur = 0;
			for (TaskTracker task : repo.findAll()) {
				if ((task.getTaskDate().getDate()>=startDate.getDate()) && (task.getTaskDate().getDate()<=endDate.getDate())) {
					System.out.println("Date is today");
					if (task.getTaskName().equals("Break")) { // break as a task is not counted
						System.out.println("break is counted");
						break;
					} // if
					else {
						if (task.getEmployee().getEmpId().equals(emp.getEmpId())) {
							System.out.println("there is a task with an employee today");
							count += 1;
							dur += task.getDuration();
						} // if

					} // else
				} // main if
			} // for
			if (count == 0)
				badEmployee.put(emp, "No tasks within given dates ");
			else if (count == 1)
				badEmployee.put(emp, "Only 1 task done within given dates with duration = " + dur / 1000 + " sec");
			else if (dur < 7200000)
				badEmployee.put(emp, "total task duration is less than 2 hours. duration = " + dur / 1000 + " sec");

		} // for
		return badEmployee;
	}


}
