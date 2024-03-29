package com.ksy.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ksy.server.domain.User;
import com.ksy.server.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User registerUser(User user) {
		userRepository.save(user);
		return user;
	}
	
	
	public boolean usernameExist(String username) {
		
		return userRepository.existsByUsername(username);
	}
}
