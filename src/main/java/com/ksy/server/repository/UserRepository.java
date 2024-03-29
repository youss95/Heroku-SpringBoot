package com.ksy.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksy.server.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsernameAndPassword(String username, String password);
	User findByPassword(String password);
	User findByUsername(String username);
	boolean existsByUsername(String username);
}
