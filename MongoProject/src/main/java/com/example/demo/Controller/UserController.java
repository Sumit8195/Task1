package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.model.response.UserResponse;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;

import io.prometheus.client.Counter;




@RestController
@Validated
public class UserController {
	
	private static Logger log =LogManager.getLogger(UserController.class);
	
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
	public EntityModel<UserResponse> create(@RequestBody @Valid UserResponse userResponse) 
	{
		
		UserResponse resp = userServ.createUser(userResponse);
		Link link = linkTo(methodOn(UserController.class).getUser(resp.getFname())).withSelfRel();
		resp.add(link);
		return EntityModel.of(resp);
	}
	@GetMapping(value="/{fname}")
	public EntityModel<UserResponse> getUser(@PathVariable String fname) 
	{
		log.info("Info FirstName ="+fname);
		log.debug("debug FirstName ="+fname);
		log.warn("warn FirstName ="+fname);
		log.error("error FirstName ="+fname);
		log.trace("trace FirstName ="+fname);
		
		Link link = linkTo(methodOn(UserController.class).getUser(fname)).withSelfRel();
		UserResponse resp = userService.getUser(fname);
		resp.add(link);
		return EntityModel.of(resp);
	}
	@DeleteMapping(value="/deleteAll")
	public String deleteAll() 
	{
		userService.deleteAll();
		return "Deleted All records";
	}
	@GetMapping(value="/getAll")
	public CollectionModel<UserResponse> getAll() 
	{
		List<UserResponse> list=userService.getAll();
		for(UserResponse resp:list) {
			Link link = linkTo(methodOn(UserController.class).getUser(resp.getFname())).withSelfRel();
			resp.add(link);
		}
		Link link = linkTo(methodOn(UserController.class).getAll()).withSelfRel();
		counter.inc();
		return CollectionModel.of(list,link); 
	}
	@PutMapping(value="/{id}")
	public EntityModel<UserResponse> update(@PathVariable long id,@RequestBody @Valid UserResponse user) 
	{
		UserResponse resp = userService.update(id,user);
		Link link = linkTo(methodOn(UserController.class).getUser(resp.getFname())).withSelfRel();
		resp.add(link);
		return EntityModel.of(resp);
	}
	@DeleteMapping("/delete/{fname}")
	public String delete(@PathVariable  String fname) 
	{
		 userService.delete(fname);
		 return "Deleted"+fname;
	}
	
	
	
}