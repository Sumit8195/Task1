package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	@Autowired
	private UserRepository userRepository;
	//create
	public User create(User user) {
		return userRepository.save(user);
	}
	//retrieve
	public List<User> getAll()
	{
		return userRepository.findAll();
	}
	//read one
	public User getByFirstname(String fname) {
		
		return userRepository.findByFname(fname);
		
	}
	//update
	public User update(long id,User user) {
		
		user.setUid(id);
		
		return userRepository.save(user);
	}
	//Delete All
	public void deleteAll() {
		userRepository.deleteAll();
		
	}
	//delete one
	public void delete(String fname) {
		User u = userRepository.findByFname(fname);
		userRepository.delete(u);
		
		
	}
	@Override
	public List<User> findPaginated(int pageno, int pagesize) {
		Pageable paging = PageRequest.of(pageno, pagesize);
		Page<User> pageResult = userRepository.findAll(paging);
		return pageResult.toList();
	}

}
