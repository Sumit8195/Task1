package com.example.demo.Controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;

import io.prometheus.client.Counter;




@RestController

public class UserController {
	
	Logger log =LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public UserService userService;
	@Autowired
	private IUserService userServ;
	
	static final Counter counter = Counter.build()
			.name("total_request")
			.help("Total number of requests")
			.register();
	
	@GetMapping(value="/{pageNo}/{pageSize}")
	public List<User> getPaginated(@PathVariable int pageNo,@PathVariable int pageSize){
		return userServ.findPaginated(pageNo, pageSize);
	}
	
	@PostMapping(value="/create")
	public String create(@RequestBody User user) 
	{
		log.info("User entered in create endpoint");
		User u = userService.create(user);
		return u.toString();
	}
	@GetMapping(value="/{fname}")
	public User getUser(@PathVariable String fname) 
	{
		return userService.getByFirstname(fname);
	}
	@DeleteMapping(value="/")
	public String deleteAll() 
	{
		userService.deleteAll();
		return "Deleted All records";
	}
	@GetMapping(value="/")
	public List<User> getAll() 
	{
		counter.inc();
		return userService.getAll();
	}
	@PutMapping(value="/{id}")
	public String update(@PathVariable long id,@RequestBody User user) 
	{
		User u = userService.update(id,user);
		return u.toString();
	}
	@DeleteMapping("/{fname}")
	public String delete(@PathVariable  String fname) 
	{
		 userService.delete(fname);
		 return "Deleted"+fname;
	}
	
	
	
}