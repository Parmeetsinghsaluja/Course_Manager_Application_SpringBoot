package com.course_manager.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.course_manager.models.User;

public interface UserRepository extends CrudRepository <User, Integer>{
	@Query("SELECT u FROM User u WHERE u.username =:username AND u.password =:password")
	Iterable<User> findUserByCredentials(
		@Param("username") String username, 
		@Param("password") String password);
	
	@Query("SELECT u FROM User u WHERE u.username =:username")
	Optional <User> findUserByUserName(
		@Param("username") String username);
}