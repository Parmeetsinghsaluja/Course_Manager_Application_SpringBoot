package com.example.myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myapp.repositories.UserRepository;
import com.example.myapp.models.User;


@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	
	@GetMapping("api/user")
	public List<User> findAllUsers (){
		return (List<User>) repository.findAll();
		
	}
}
