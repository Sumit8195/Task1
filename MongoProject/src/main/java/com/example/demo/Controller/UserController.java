package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;


@RestController
public class UserController {
	@Autowired
	public UserRepository userRepository;
	
	@GetMapping(value="/all")
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	@GetMapping(value="/user/{id}")
	public Optional<User> getUser(@PathVariable long id)
	{
		return userRepository.findById(id);
	}
	@PostMapping(value="/create")
	public String createUser(@RequestBody User user)
	{
		User insertedUser = userRepository.insert(user);
		return "User created "+insertedUser.getUid();
	}
	@DeleteMapping(value="/delete/{id}")
	public String deleteUser(@PathVariable long id)
	{
		userRepository.deleteById(id);
		return "user deleted with id :"+id;
	}
	@PutMapping(value="/user")
	public String updateuser(@RequestBody User user)
	{
		User upd=userRepository.save(user);
		return "User updated "+upd.getUid();
	}
	
	
}