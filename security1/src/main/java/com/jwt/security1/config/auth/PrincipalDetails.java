package com.jwt.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.jwt.security1.model.User;

import lombok.Data;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킴
// 로그인이 진행이 완료가 되면 '시큐리티 session'을 만들어준다.(Security ContextHolder)
// 오브젝트 타입 => Authentication 객체 
// Authentication 안에 User 정보가 있어야 됨
// User 오브젝트 타입 => UserDetails 타입 객체

// Security Session 안에 Authentication 안에 UserDetails(PrincipalDetails)
@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private User user;
	private Map<String,Object> attributes;
	
	// 일반 로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	// OAuth 로그인
	public PrincipalDetails(User user, Map<String,Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	// 해당 User의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
		collect.add(()->{return user.getRole();});
		return collect;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 이 새끼 false로 되있으면 password expire 됨 ㅡㅡ
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 우리 사이트!! 1년동안 회원이 로그인 안하면 휴면 계정으로 하기로 함
		// 현재시간 - 로그인시간 => 1년 초과하면 return false;
		return true;
	}

	// ---- OAuth2User Method ----- //
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
