package com.example.demo.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
	public Optional<User> findByFname(String fname);
	@Override
	List<User> findAll();
	public void deleteByFname(String fname);
	
	

}
