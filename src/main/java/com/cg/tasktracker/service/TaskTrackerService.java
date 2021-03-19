package com.cg.tasktracker.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.tasktracker.dao.EmployeeRepository;
import com.cg.tasktracker.dao.TaskTrackerRepository;
import com.cg.tasktracker.entity.EmployeeEntity;
import com.cg.tasktracker.entity.TaskTracker;
import com.cg.tasktracker.exceptions.CustomException;
import com.cg.tasktracker.model.EmpTaskModel;

@Service
public class TaskTrackerService {

	@Autowired
	TaskTrackerRepository repo;

	@Autowired
	EmployeeRepository empRepo;

	public TaskTracker addTask(TaskTracker tasktracker) {

		tasktracker.setTaskDate(new java.sql.Date(System.currentTimeMillis()));
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.HOUR_OF_DAY) < 9 || cal.get(Calendar.HOUR_OF_DAY) >= 18)
			throw new CustomException("Task cannot be added other than office hours");
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
//			Calendar cal = Calendar.getInstance();
//			if (cal.get(Calendar.HOUR) >= 18) {
//				cal.setTimeInMillis(task.getStartTime().getTime());
//				System.out.println(cal);
//				cal.set(Calendar.HOUR, 18);
//				cal.set(Calendar.MINUTE, 1);
//				cal.set(Calendar.SECOND, 0);
//			}
//			if (cal.get(Calendar.HOUR) < 9)
//				throw new CustomException("Task cannot end before office starts");

			task.setEndTime(new Timestamp(System.currentTimeMillis()));
			System.out.println("end time:" + task.getEndTime());
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

//	public EmpTaskModel badtask(Date startdate, Date enddate) { // bad task if running for 4 hours and above
//		Map<TaskTracker, String> badTask1 = new HashMap<TaskTracker, String>();
//
//		List<TaskTracker> badTask = new ArrayList<>();
//		EmpTaskModel EmpTaskModel = new EmpTaskModel();
//
//		EmpTaskModel.setCount(0);
//		EmpTaskModel.setTotalDuration(0);
//		for (TaskTracker task : repo.findAll()) {
//			if ((task.getTaskDate().getDate() >= startdate.getDate())
//					&& (task.getTaskDate().getDate() <= enddate.getDate())) {
//				if (task.getTaskName().equalsIgnoreCase("break")) {
//
//				} // for
//				else {
//					if (task.getEndTime() == null) {
//						if (Double.valueOf(
//								(new Timestamp(System.currentTimeMillis()).getTime() - task.getStartTime().getTime())
//										/ 3600000) >= 4) {
//							badTask1.put(task, "task running for 4 hours and above");
//							badTask.add(task);
//							EmpTaskModel.setCount(EmpTaskModel.getCount() + 1);
//							EmpTaskModel.setTotalDuration(EmpTaskModel.getTotalDuration() + task.getDuration());
//
//						} // second if
//					} // first if
//					else {
//						if (task.getDuration() > 14400000)
//							badTask1.put(task, "task runnong for 4 hours and above");
//						badTask.add(task);
//						EmpTaskModel.setCount(EmpTaskModel.getCount() + 1);
//						EmpTaskModel.setTotalDuration(EmpTaskModel.getTotalDuration() + task.getDuration());
//					}
//				} // else
//			} // main if
//		} // for
//		EmpTaskModel.setEmployee(null);
//		EmpTaskModel.setTask(badTask);
//		return EmpTaskModel;
//	}

	public void setEndTime() { // call this function on ngOnInit() which will end the tasks running more than 5
		// hrs to current time

		for (TaskTracker task : repo.findAll()) {
			Calendar cal = Calendar.getInstance();
			if (task.getEndTime() == null && cal.get(Calendar.HOUR_OF_DAY) >= 18) {
				cal.setTimeInMillis(task.getStartTime().getTime());
				System.out.println(cal);
				cal.set(Calendar.HOUR, 18);
				cal.set(Calendar.MINUTE, 1);
				cal.set(Calendar.SECOND, 0);
			task.setEndTime(new Timestamp(cal.getTimeInMillis()));
			repo.save(task);
			}// if
		} // for
	}

//	public List<EmpTaskModel> badEmployee() {
//
//		
//		List<EmpTaskModel> EmpTaskModel = new ArrayList<>();
//
//
//		for (EmployeeEntity emp : empRepo.findAll()) {
//			EmpTaskModel bad = new EmpTaskModel();
//			List<TaskTracker> badTask = new ArrayList<>();
//			bad.setTotalDuration(0);
//			for (TaskTracker task : repo.findAll()) {
//
//				if (task.getTaskDate().getDate() == (Date.from(Instant.now().minus(Duration.ofDays(1)))).getDate()) {
//					System.out.println("Date is yesterday");
//					if (task.getTaskName().equalsIgnoreCase("Break")) { // break as a task is not counted
//						System.out.println("break is counted");
//						break;
//					} // if
//					else {
//						if (task.getEmployee().getEmpId().equals(emp.getEmpId())) {
//							System.out.println("there is a task with an employee today");
//							bad.setTotalDuration(bad.getTotalDuration() + task.getDuration());
//							badTask.add(task);
//
//							// count += 1;
//							// dur += task.getDuration();
//						} // if
//
//					} // else
//				} // main if
//			} // for
//
//			bad.setEmpId(emp.getEmpId());
//			bad.setName(emp.getName());
//			bad.setTask(badTask);
//			EmpTaskModel.add(bad);
//
//		} // for
//			// return badEmployee;
//		return EmpTaskModel;
//	}

	
	
	public List<EmpTaskModel> getBadEmployee(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 18);
		cal.set(Calendar.MINUTE, 1);
		cal.set(Calendar.SECOND, 0);
		System.out.println(new Timestamp(cal.getTimeInMillis()));
		Timestamp time = new Timestamp(cal.getTimeInMillis());
		System.out.println(time);
		List<TaskTracker> badTasks = repo.findBadTaskByDate(date, time);
		List<EmpTaskModel> empBadTasks = new ArrayList<EmpTaskModel>();
		int length=0;
		int firstFlag=0;
		for(TaskTracker badTask: badTasks) {
			int flag=0;
			for (int i = 0; i <= length; i++) {
				if(empBadTasks.isEmpty()) {
					empBadTasks.add(addBadTask(badTask));
					flag=1;
				}else if (length != 0 || firstFlag == 1) {
					if(badTask.getEmployee().getEmpId()==empBadTasks.get(i).getEmpId()) {
						EmpTaskModel model = empBadTasks.get(i);
						model.setTotalDuration(model.getTotalDuration()+badTask.getDuration());
						List<TaskTracker> tasks = model.getTasks();
						tasks.add(badTask);
						model.setTasks(tasks);
						empBadTasks.set(i, model);
						flag = 1;
					}
				}
			}
			
			if (flag == 0) {
				empBadTasks.add(addBadTask(badTask));
		        length = length + 1;
		      }
		      if (empBadTasks.size() == 1) {
		        firstFlag = 1;
		      }
		      flag = 0;
		}
		System.out.println(empBadTasks);
		
		return empBadTasks;
	}
	
	public EmpTaskModel addBadTask(TaskTracker badTask) {
		EmpTaskModel badEmpTask = new EmpTaskModel();
		badEmpTask.setEmpId(badTask.getEmployee().getEmpId());
		badEmpTask.setName(badTask.getEmployee().getName());
		badEmpTask.setTotalDuration(badTask.getDuration());
		List<TaskTracker> badTasks = new ArrayList<TaskTracker>();
		badTasks.add(badTask);
		badEmpTask.setTasks(badTasks);
		
		return badEmpTask;
	}
	public List<EmpTaskModel> badEmployeeAccToDate(Date startDate, Date endDate) {
		// Map<EmployeeEntity, String> badEmployee = new HashMap<EmployeeEntity,
		// String>();
		List<EmpTaskModel> EmpTaskModel = new ArrayList<>();
		 int count = 0;
		// long dur = 0;
		for (EmployeeEntity emp : empRepo.findAll()) {
			EmpTaskModel bad = new EmpTaskModel();
			List<TaskTracker> badTask = new ArrayList<>();
			
			bad.setTotalDuration(0);
			 count = 0;
			// dur = 0;
			for (TaskTracker task : repo.findAll()) {
			if ((task.getTaskDate().compareTo(startDate)>=0) && (task.getTaskDate().compareTo(endDate)<=0)) {
					if (task.getTaskName().equals("Break")) {
						// break as a task is not counted
						System.out.println("break is counted");
						break;
					} // if
					else {
						if (task.getEmployee().getEmpId().equals(emp.getEmpId())) {
							System.out.println("there is a task with an employee today");
							bad.setTotalDuration(bad.getTotalDuration() + task.getDuration());
							badTask.add(task);
							 count += 1;
							// dur += task.getDuration();
						} // if

					} // else
				} // main if
			} // for
			bad.setEmpId(emp.getEmpId());
			bad.setTasks(badTask);
			if (count == 0)
				EmpTaskModel.add(bad);        //only if employee has not done any task then he is an bad employee
//				badEmployee.put(emp, "No tasks within given dates ");
			//else if (bad.getCount() == 1)
				//EmpTaskModel.add(bad);
//				badEmployee.put(emp, "Only 1 task done within given dates with duration = " + dur / 1000 + " sec");
			//else if (bad.getCount() > 1 && bad.getTotalDuration() < 7200000)
				//EmpTaskModel.add(bad);
//				badEmployee.put(emp, "total task duration is less than 2 hours. duration = " + dur / 1000 + " sec");

		} // for
			// return badEmployee;
		return EmpTaskModel;
	}

}