package com.example.demo.repositories;



import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.model.User;


@Repository
public interface UserRepository extends MongoRepository<User, Long>{
	
	
	public Optional<User> findById(Long id);
	
	
}
