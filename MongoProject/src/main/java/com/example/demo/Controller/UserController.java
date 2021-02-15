package com.example.demo.Controller;

import java.util.List;


import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.repository.query.Param;
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
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;
import io.micrometer.core.annotation.Timed;


@RestController
@Validated
public class UserController {
	
	@Autowired
	public UserService userService;
	@Autowired
	private IUserService userServ;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@PostMapping(value="/user")
	public EntityModel<UserDTO> create(@RequestBody @Valid UserDTO userResponse) 
	{
		
		UserDTO resp = userServ.createUser(userResponse);
		Link link = linkTo(methodOn(UserController.class).getUser(resp.getUserId())).withSelfRel();
		resp.add(link);
		return EntityModel.of(resp);
	}
	@Timed(value="Get Request",
			histogram = true)
	@GetMapping(value="/user/{uid}")
	public EntityModel<UserDTO> getUser(@PathVariable long uid) 
	{
		
		log.info("Got  info");
		log.debug("Got debug");
		log.error("Got an error");
		log.warn("Got warn msg");
		Link link = linkTo(methodOn(UserController.class).getUser(uid)).withSelfRel();
		UserDTO resp = userService.getUser(uid);
		resp.add(link);
		return EntityModel.of(resp);
	}
	
	
	
	@GetMapping(value="/user")
	public CollectionModel<UserDTO> getAll() 
	{
		List<UserDTO> list=userService.getAll();
		for(UserDTO resp:list) {
			Link link = linkTo(methodOn(UserController.class).getUser(resp.getUserId())).withSelfRel();
			resp.add(link);
		}
		Link link = linkTo(methodOn(UserController.class).getAll()).withSelfRel();
		return CollectionModel.of(list,link); 
	}
	@GetMapping(value="/user/getbyemail")
	public UserDTO getbyEmail(@Param(value="userEmail") String userEmail) 
	{
		UserDTO resp = userService.getbyEmail(userEmail);
		return resp;
	}
	
	@GetMapping(value="/user/getbycontact")
	public UserDTO getbyContact(@Param(value="userContact") long userContact) 
	{
		UserDTO resp = userService.getbyContact(userContact);
		return resp;
	}
	

	@GetMapping("/user/pageable")
	public Page<User> retrieveUserWithPaging(@Param(value="Page") int Page,@Param(value="Size") int Size){
		return userService.retrieveUserWithPaging(Page-1,Size);
	}
	
	
	
	@PutMapping(value="/user")
	public EntityModel<UserDTO> update(@RequestBody @Valid UserDTO user) 
	{
		UserDTO resp = userService.update(user);
		Link link = linkTo(methodOn(UserController.class).getUser(resp.getUserId())).withSelfRel();
		resp.add(link);
		return EntityModel.of(resp);
	}
	@DeleteMapping("/user/{uid}")
	public String delete(@PathVariable long uid) 
	{
		 userService.delete(uid);
		 return "Deleted user with id ="+uid;
	}
	
	
	
}