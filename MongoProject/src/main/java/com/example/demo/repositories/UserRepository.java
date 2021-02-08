package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, Long> {
	public User findByFname(String fname);
	@Override
	List<User>findAll();
	

}
