package com.jwt.security1.config.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jwt.security1.model.User;
import com.jwt.security1.repository.UserRepository;

// 시큐리티 설정에서 loginProcessingUrl("/login")
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는
// loadUserByUsername 이 실행된다.

@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired private UserRepository userRepository;
	
	// 시큐리티 session(내부 Authentication(내부 UserDetails))
	// return 하게 되면 자동으로 Authentication 객체에 UserDetails를 넣어준다
	// 이후 Authentication 를 시큐리티 세션에 넣어준다.
	// 함수 종료 시 @Athentication 어노테이션이 만들어진다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		PrincipalDetails principalDetails = new PrincipalDetails(user);
		// User 클래스는 UserDetails 인터페이스를 구현한다. 
		// UserDetailsService 는 AthenticationProvider로 검증된 인증 객체를 보낸다.
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority(user.getRole()));
//		return new org.springframework.security.core.userdetails.User(
//				user.getUsername(), user.getPassword(), authorities);
//		
		return principalDetails;
	}
}
