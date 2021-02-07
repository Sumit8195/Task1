package com.example.demo.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

import com.example.demo.service.UserService;


@RestController
public class UserController {
	@Autowired
	public UserService userService;
	
	@RequestMapping("/create")
	public String create(@RequestParam String fname,@RequestParam String lname,@RequestParam String uemail,@RequestParam long ucontact,@RequestParam long uid) 
	{
		User u = userService.create(fname, lname, uemail, ucontact, uid);
		return u.toString();
	}
	@RequestMapping("/get")
	public User getUser(@RequestParam String fname) 
	{
		return userService.getByFirstname(fname);
	}
	@RequestMapping("/deleteAll")
	public String deleteAll() 
	{
		userService.deleteAll();
		return "Deleted All records";
	}
	@RequestMapping("/getAll")
	public List<User> getAll() 
	{
		return userService.getAll();
	}
	@RequestMapping("/update")
	public String update(@RequestParam String fname,@RequestParam String lname,@RequestParam String uemail,@RequestParam long ucontact,@RequestParam long uid) 
	{
		User u = userService.create(fname, lname, uemail, ucontact, uid);
		return u.toString();
	}
	@RequestMapping("/delete")
	public String delete(@RequestParam String fname) 
	{
		 userService.delete(fname);
		 return "Deleted"+fname;
	}
	
	
	
}