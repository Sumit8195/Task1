package com.example.demo.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.controller.UserController;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	//create
	@Override
	public UserDTO createUser(UserDTO userResponse) 
	{
		log.info("Got request to create user");
		User user =new User();
		BeanUtils.copyProperties(userResponse, user);
		user = userRepository.save(user);
		BeanUtils.copyProperties(user, userResponse);
		if(user!=null){
			log.info("User created");
			return userResponse;
		}
		else
		{
			log.error("User not created");
			throw new UserNotFoundException("Something went wrong");
		}
	}
	//read one
	public UserDTO getUser(Long uid) {
		log.info("Got request to find user with userId"+uid);
	Optional<User> user= userRepository.findById(uid);
	UserDTO resp=null;
	if(user.isPresent())
	{
		log.info("User found with userId"+uid);
		resp= new UserDTO();
		BeanUtils.copyProperties(user.get(), resp);
		return resp;
	}
	else {
		log.error("No user found");
		throw new UserNotFoundException("No user with id "+uid);
	}
		
	}
	//update
	public UserDTO update(UserDTO user) {
		log.info("Got request to update user");
			User user1 = new User();
			BeanUtils.copyProperties(user, user1);
			user1=userRepository.save(user1);
			BeanUtils.copyProperties(user1, user);
			if(user1!=null)
			{
				log.info("User updated");
				return user;
			}
			else
			{
				log.error("User not udated");
				throw new UserNotFoundException("Something went wrong");
			}
	}
	
	//delete one
	public void delete(Long uid) {
		log.info("Got request to delete user with userId"+uid);
		Optional<User> user= userRepository.findById(uid);
		if(user.isPresent())
		{
			log.info("User deleted with userId"+uid);
			userRepository.deleteById(uid);
		}	
		else {
			
			throw new UserNotFoundException("No user with id "+uid);
		}
	}
	
	
	//Get All user with pages
	
	
	public Page<UserDTO> getAll(Optional<Long> userId, Optional<Long> userContact, Optional<String> userEmail,
			Optional<String> firstName, Optional<String> lastName, int page, int size)
	{
		List<User> list= new ArrayList<User>();
		Pageable pageing=PageRequest.of(page, size);
		if(userId.isEmpty()&&userContact.isEmpty()&&userEmail.isEmpty()&&firstName.isEmpty()&&lastName.isEmpty())
		{
			
			
			Page<User> pages = userRepository.findAll(pageing);
			list = pages.getContent();
		}
		else
		{
		User user= new User();
		if(userId.isEmpty())
			user.setUserId(null);
		else
			user.setUserId(userId.get());
		if(userContact.isEmpty())
			user.setUserContact(null);
		else
			user.setUserContact(userContact.get());
		if(userEmail.isEmpty())
			user.setUserEmail(null);
		else
			user.setUserEmail(userEmail.get());
		if(firstName.isEmpty())
			user.setFirstName(null);
		else
			user.setFirstName(firstName.get());
		if(lastName.isEmpty())
			user.setLastName(null);
		else
			user.setLastName(lastName.get());
		Example<User> u = Example.of(user);
		list= userRepository.findAll(u);
		
		}
		List<UserDTO> dtolist = new ArrayList<>();
		for(User users:list) {
			UserDTO dto= new UserDTO();
			BeanUtils.copyProperties(users, dto);
			dto.add(linkTo(methodOn(UserController.class).getUser(users.getUserId())).withSelfRel());
			dtolist.add(dto);	
		}
		if(dtolist.isEmpty())
			throw new UserNotFoundException("No pages found");
		else
		{
			
			int end = (0 + pageing.getPageSize())>dtolist.size()?dtolist.size():(0+pageing.getPageSize());
			Page<UserDTO> pages = new PageImpl<UserDTO>(dtolist.subList(0, end),pageing,dtolist.size());
			return pages;
		}
	}
	
}
