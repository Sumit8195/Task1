package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.DTO.UserDTO;
@Service
public interface IUserService {
	
	public UserDTO createUser(UserDTO userResponse);
	public UserDTO getUser(long uid);
	public UserDTO update(UserDTO userResponse);
	public void delete(long uid);
	public List<UserDTO> getAll();
	public Page<User> retrieveUserWithPaging(int page, int size);

	
	
	

	
}
