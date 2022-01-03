package com.example.jwt.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// http:localhost:8080/login => 동작 안함
// 필터 만들어서 동작하게 해주면 됨
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("loaduserByUsername()");
		User user = userRepository.findByUsername(username);
		System.out.println("findUser :"+ user);
		return new PrincipalDetails(user);
	}

}
