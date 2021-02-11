package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.model.DTO.UserDTO;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	//create
	@Override
	public UserDTO createUser(UserDTO userResponse) {
		User user =new User();
		BeanUtils.copyProperties(userResponse, user);
		user = userRepository.save(user);
		BeanUtils.copyProperties(user, userResponse);
		return userResponse;
	}
	//retrieve
	public List<UserDTO> getAll()
	{
		Iterable<User> Iterable= userRepository.findAll();
		List<UserDTO> users=StreamSupport.stream(Iterable.spliterator(), false).map(user->{
			UserDTO resp = new UserDTO();
			BeanUtils.copyProperties(user, resp);
			return resp;
		}).collect(Collectors.toList());
		if(users==null)
			{
			throw new UserNotFoundException("No Users in Database");
			}
		else
			return users;
	}
	//read one
	public UserDTO getUser(long uid) {	
	Optional<User> user= userRepository.findById(uid);
	UserDTO resp=null;
	if(user.isPresent())
	{
		resp= new UserDTO();
		BeanUtils.copyProperties(user.get(), resp);
		return resp;
	}
	else 
		throw new UserNotFoundException("No user with id "+uid);
		
	}
	//update
	public UserDTO update(UserDTO user) {
			User user1 = new User();
			BeanUtils.copyProperties(user, user1);
			user1=userRepository.save(user1);
			BeanUtils.copyProperties(user1, user);
		return user; 
	}
	
	//delete one
	public void delete(long uid) {
		
		Optional<User> user= userRepository.findById(uid);
		if(user.isPresent())
		{
			
			userRepository.deleteById(uid);
		}	
		else 
			throw new UserNotFoundException("No user with id "+uid);
	}
	@Override
	public List<User> findPaginated(int pageno, int pagesize) {
		Pageable paging = PageRequest.of(pageno, pagesize);
		Page<User> pageResult = userRepository.findAll(paging);
		return pageResult.toList();
	}

}
