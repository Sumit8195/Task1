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

import com.example.demo.model.User;
import com.example.demo.model.response.UserResponse;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserResponse createUser(UserResponse userResponse) {
		User user =new User();
		BeanUtils.copyProperties(userResponse, user);
		user = userRepository.save(user);
		BeanUtils.copyProperties(user, userResponse);
		return userResponse;
	}
	
	//create

	//retrieve
	public List<UserResponse> getAll()
	{
		Iterable<User> Iterable= userRepository.findAll();
		List<UserResponse> users=StreamSupport.stream(Iterable.spliterator(), false).map(user->{
			UserResponse resp = new UserResponse();
			BeanUtils.copyProperties(user, resp);
			return resp;
		}).collect(Collectors.toList());
		return users;
	}
	//read one
	public UserResponse getUser(String fname) {
		
	Optional<User> user= userRepository.findByFname(fname);
	UserResponse resp=null;
	if(user.isPresent())
	{
		resp= new UserResponse();
		BeanUtils.copyProperties(user.get(), resp);
		
	}
		return resp;
	}
	//update
	public UserResponse update(long id,UserResponse user) {
		
		Optional<User> useropt = userRepository.findById(id);
		
		if(useropt.isPresent()) {
			User user1 = new User();
			BeanUtils.copyProperties(user, user1);
			user1=userRepository.save(user1);
			BeanUtils.copyProperties(user1, user);
		}
		else
		{
			//Exception
		}
		
		return user; 
	}
	//Delete All
	public void deleteAll() {
		userRepository.deleteAll();
		
	}
	//delete one
	public void delete(String fname) {
		
		Optional<User> user= userRepository.findByFname(fname);
		
		
		if(user.isPresent())
		{
			
			userRepository.deleteByFname(fname);
		}
		else
		{
			//Exception
		}
		
		
	}
	@Override
	public List<User> findPaginated(int pageno, int pagesize) {
		Pageable paging = PageRequest.of(pageno, pagesize);
		Page<User> pageResult = userRepository.findAll(paging);
		return pageResult.toList();
	}

}
