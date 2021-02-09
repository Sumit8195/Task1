package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.response.UserResponse;
@Service
public interface IUserService {
	List<User> findPaginated(int pageno,int pagesize);
	public UserResponse createUser(UserResponse userResponse);
	public UserResponse getUser(String fname);
	public UserResponse update(long id,UserResponse userResponse);
	public void delete(String fname);
	public List<UserResponse> getAll();
}
