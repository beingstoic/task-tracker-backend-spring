package com.cg.tasktracker.dao;

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
	
	@Query("Select t FROM TaskTracker t WHERE t.employee.empId =:empId AND t.endTime IS NULL ")
	TaskTracker checkRunningTask(@Param(value="empId") String empId);
	
	@Query("Select t FROM TaskTracker t WHERE t.employee.empId =:empId AND t.taskId =:taskId ")
	TaskTracker getAssignedTask(@Param(value="taskId") Long taskId, @Param(value="empId") String empId);
	
	
	@Query("Select t From TaskTracker t Where t.employee.empId=:empId")
	List<TaskTracker> fetchAllByEmpId(@Param(value="empId")String empId);
}
