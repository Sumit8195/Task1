package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.service.IUserService;
import com.example.demo.service.UserService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Validated
@Api(value="Users")
public class UserController {
	
	@Autowired
	public UserService userService;
	@Autowired
	private IUserService userServ;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@ApiOperation(value = "Add a user")
	@SuppressWarnings("unused")
	@PostMapping(value="/user")
	public EntityModel<UserDTO> create(@RequestBody @Valid UserDTO userResponse) 
	{
		log.info("Got request to create user");
		UserDTO resp = userServ.createUser(userResponse);
		Link link = linkTo(methodOn(UserController.class).getUser(resp.getUserId())).withSelfRel();
		resp.add(link);
		if(resp!=null){
			log.info("User created");
			return EntityModel.of(resp);
		}
		else
		{
			log.error("User not created");
			throw new UserNotFoundException("Something went wrong");
		}
	}
	@SuppressWarnings("unused")
	@ApiOperation(value = "Search a user with an ID",response = User.class)
	@GetMapping(value="/user/{uid}")
	public EntityModel<UserDTO> getUser(@PathVariable Long uid) 
	{
		
		log.info("Got request to find user with userId"+uid);
		Link link = linkTo(methodOn(UserController.class).getUser(uid)).withSelfRel();
		UserDTO resp = userService.getUser(uid);
		resp.add(link);
		if(resp!=null)
		{
			log.info("User found with userId"+uid);
			return EntityModel.of(resp);
		}
		{
			log.error("No user found");
			throw new UserNotFoundException("No user found");
		}
	}
	
	@ApiOperation(value = "View a Pages of users",response = Iterable.class)
	@GetMapping("/user")
	public Page<UserDTO> getall(@RequestParam(required=false,value="userId")Optional<Long> userId,@RequestParam(required=false,value="userContact")Optional<Long> userContact,
			@RequestParam(required=false,value="userEmail")Optional<String> userEmail,@RequestParam(required=false,value="firstName")Optional<String> firstName,
			@RequestParam(required=false,value="lastName")Optional<String> lastName,@RequestParam(defaultValue="0",value="Page")int page,
			@RequestParam(defaultValue="3",value="Size")int size)
	{
		return userServ.getAll(userId,userContact,userEmail,firstName,lastName,page,size);
	}
	
	@ApiOperation(value = "Update a user")
	@SuppressWarnings("unused")
	@PutMapping(value="/user")
	public EntityModel<UserDTO> update(@RequestBody @Valid UserDTO user) 
	{
		log.info("Got request to update user");
		UserDTO resp = userService.update(user);
		Link link = linkTo(methodOn(UserController.class).getUser(resp.getUserId())).withSelfRel();
		resp.add(link);
		if(resp!=null)
		{
			log.info("User updated");
			return EntityModel.of(resp);
		}
		else
		{
			log.error("User not udated");
			throw new UserNotFoundException("Something went wrong");
		}
	}
	@ApiOperation(value = "Delete a user")
	@DeleteMapping("/user/{uid}")
	public ResponseEntity<String> delete(@PathVariable Long uid) 
	{
		log.info("Got request to delete user with userId"+uid);
		 userService.delete(uid);
		 return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
	}
	
	
	
}