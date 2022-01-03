package com.jwt.security1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jwt.security1.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	// select * from user where username = ? (String username)
	public User findByUsername(String username);
}
