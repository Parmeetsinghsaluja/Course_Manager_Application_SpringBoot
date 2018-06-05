package com.course_manager.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.course_manager.models.User;
import com.course_manager.repositories.UserRepository;


@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}
	
	@PostMapping("/api/user")
	@ResponseBody
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@PostMapping("/api/reg")
	@ResponseBody
	public User registerUser(@RequestBody User user) {
		Optional <User> data = repository.findUserByUserName(user.getUsername());
		if(data.isPresent()) {
			System.out.println("User found");
			return null;
		}
		else {
			return createUser(user);
		}
	}
	

	@PostMapping("/api/login")
	@ResponseBody
	public User loginUser(@RequestBody User user){
		List <User> list =(List<User>) repository.findUserByCredentials(user.getUsername(), user.getPassword());
		if (list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@PutMapping("/api/user/{userId}")
	@ResponseBody
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setLastName(newUser.getLastName());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		else {
			return null;
		}
	}
	
	@PutMapping("/api/profile")
	@ResponseBody
	public User updateProfile(@RequestBody User newUser) {
		Optional<User> data = repository.findUserByUserName(newUser.getUsername());
		if(data.isPresent()) {
			User user = data.get();
			user.setEmail(newUser.getEmail());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setPhone(newUser.getPhone());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		else {
			return null;
		}	
	}
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId){
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		}
	}
	
	@GetMapping("/api/profile/{username}")
	@ResponseBody
	public Integer findUserIdByUsername(@PathVariable("username") String username){
		Optional<User> data = repository.findUserByUserName(username);
		if(data.isPresent()) {
			return data.get().getId();
		}
		else {
			return null;
		}
	}
}
