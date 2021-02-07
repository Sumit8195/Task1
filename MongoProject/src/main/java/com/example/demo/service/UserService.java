package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	//create
	public User create(String fname,String lname,String uemail,long ucontact, long uid) {
		return userRepository.save(new User(fname,lname,uemail,ucontact,uid));
	}
	//retrieve
	public List<User> getAll()
	{
		return userRepository.findAll();
	}
	//read one
	public User getByFirstname(String fname) {
		return userRepository.findByFname(fname);
		
	}
	//update
	public User update(String fname,String lname,String uemail,long ucontact, long uid) {
		User u = userRepository.findByFname(fname);
		u.setLname(lname);
		u.setUcontact(ucontact);
		u.setUemail(uemail);
		u.setUid(uid);
		return userRepository.save(u);
	}
	//Delete All
	public void deleteAll() {
		userRepository.deleteAll();
		
	}
	//delete one
	public void delete(String fname) {
		User u = userRepository.findByFname(fname);
		userRepository.delete(u);
		
		
	}

}
