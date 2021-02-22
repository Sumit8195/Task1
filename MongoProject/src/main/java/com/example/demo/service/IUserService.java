package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;

@Service
public interface IUserService {
	
	public UserDTO createUser(UserDTO userResponse);
	public UserDTO getUser(Long uid);
	public UserDTO update(UserDTO userResponse);
	public void delete(Long uid);
	public Page<UserDTO> getAll(Optional<Long> userId, Optional<Long> userContact, Optional<String> userEmail,
			Optional<String> firstName, Optional<String> lastName, int page, int size);
	
}
