package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
@Service
public interface IUserService {
	List<User> findPaginated(int pageno,int pagesize);

}
