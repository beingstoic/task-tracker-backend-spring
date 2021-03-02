package com.cg.tasktracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.tasktracker.entity.UserEntity;


@Repository
public interface UserDao extends JpaRepository<UserEntity, Long>{
	@Query("SELECT p from UserEntity p  where p.email= :email AND p.password= :password")
    UserEntity validateUser(@Param("email") String email,@Param("password") String password);

	@Query("SELECT p from UserEntity p  where p.email= :email")
	UserEntity findByUsername(@Param("email") String email);
	
	boolean existsByEmail(String email);
}
