package com.example.demo.Controller;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	@GetMapping(value="/user/{pageNo}/{pageSize}")
	public List<User> getPaginated(@RequestParam int pageNo,@RequestParam int pageSize){
		return userServ.findPaginated(pageNo, pageSize);
	}
	
	@PostMapping(value="/user")
	public EntityModel<UserDTO> create(@RequestBody @Valid UserDTO userResponse) 
	{
		
		UserDTO resp = userServ.createUser(userResponse);
		Link link = linkTo(methodOn(UserController.class).getUser(resp.getUid())).withSelfRel();
		resp.add(link);
		return EntityModel.of(resp);
	}
	@Timed(value="Get Request",
			histogram = true)
	@GetMapping(value="/user/{uid}")
	public EntityModel<UserDTO> getUser(@RequestParam long uid) 
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
			Link link = linkTo(methodOn(UserController.class).getUser(resp.getUid())).withSelfRel();
			resp.add(link);
		}
		Link link = linkTo(methodOn(UserController.class).getAll()).withSelfRel();
		return CollectionModel.of(list,link); 
	}
	@PutMapping(value="/user")
	public EntityModel<UserDTO> update(@RequestBody @Valid UserDTO user) 
	{
		UserDTO resp = userService.update(user);
		Link link = linkTo(methodOn(UserController.class).getUser(resp.getUid())).withSelfRel();
		resp.add(link);
		return EntityModel.of(resp);
	}
	@DeleteMapping("/user/{uid}")
	public String delete(@RequestParam long uid) 
	{
		 userService.delete(uid);
		 return "Deleted user with id ="+uid;
	}
	
	
	
}