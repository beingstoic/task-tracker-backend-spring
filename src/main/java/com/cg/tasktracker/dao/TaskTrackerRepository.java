package com.cg.tasktracker.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.tasktracker.entity.TaskTracker;

@Repository
public interface TaskTrackerRepository extends JpaRepository<TaskTracker, Long> {

	//boolean existsByTaskName(String taskName);
	boolean existsByTaskId(Long taskId);
	List<TaskTracker> findAllByTaskName(String taskName);
	TaskTracker findByTaskId(Long taskId);
	
	@Query("Select t FROM TaskTracker t WHERE t.taskDate =:taskDate")
	List<TaskTracker> getTaskAccToDate(@Param(value="taskDate") Date taskDate);
	
	@Query("Select t FROM TaskTracker t WHERE t.employee.empId =:empId AND t.endTime IS NULL ")
	TaskTracker checkRunningTask(@Param(value="empId") String empId);
	
	@Query("Select t FROM TaskTracker t WHERE t.employee.empId =:empId AND t.taskId =:taskId ")
	TaskTracker getAssignedTask(@Param(value="taskId") Long taskId, @Param(value="empId") String empId);
	
	
	@Query("Select t From TaskTracker t Where t.employee.empId=:empId")
	List<TaskTracker> fetchAllByEmpId(@Param(value="empId")String empId);
	
	@Query("Select t FROM TaskTracker t WHERE t.employee.empId =:empId AND t.taskDate =:taskDate ") //search by empid and date
	List<TaskTracker> searchTasks(@Param(value="taskDate") Date taskDate, @Param(value="empId") String empId);
	@Query("Select t FROM TaskTracker t WHERE t.taskName =:taskName AND t.taskDate =:taskDate ") //search by taskName and date
	List<TaskTracker> searchTaskNamebyDate(@Param(value="taskName") String taskName,@Param(value="taskDate") Date taskDate);

	@Query("Select t FROM TaskTracker t WHERE t.employee.empId =:empId AND t.taskName =:taskName AND t.taskDate =:taskDate ") //search by empId,TaskName and Date
	List<TaskTracker> searchbyEmpIdTaskNameDate(@Param(value="empId") String empId,@Param(value="taskName") String taskName,@Param(value="taskDate") Date taskDate);


	@Query("Select t From TaskTracker t Where t.employee.empId=:empId AND t.taskName =:taskName")
	List<TaskTracker> fetchAllByEmpIdandTaskName(@Param(value="empId")String empId,@Param(value="taskName") String taskName);
	
	
}
