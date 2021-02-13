package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.DTO.UserDTO;
@Service
public interface IUserService {
	List<User> findPaginated(int pageno,int pagesize);
	public UserDTO createUser(UserDTO userResponse);
	public UserDTO getUser(long uid);
	public UserDTO update(UserDTO userResponse);
	public void delete(long uid);
	public List<UserDTO> getAll();
}
