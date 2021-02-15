package com.example.demo.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.User;


@Repository
public interface UserRepository extends MongoRepository<User, Long>,PagingAndSortingRepository<User,Long> {
	public Optional<User> findById(long id);
	@Override
	List<User> findAll();
	Page<User> findAll(Pageable pageable);
	

	
	
	

}
