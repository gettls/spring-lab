package com.example.jwt.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jwt.config.auth.PrincipalDetails;
import com.example.jwt.model.User;
import com.example.jwt.repository.UserRepository;

// 시큐리티가 filter를 가지고 있는데 그 필터 중 BasicAuthenticationFilter라는 것이 있음
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되있음
// 만약 권한이나 인증이 필요한 주소가 아니라면 이 필터를 타지 않음
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	
	private UserRepository userRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}
	
	
	// 인증이나 권한이 필요한 주소요청이 있을 때 해당 필터를 타게 됨
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("인증이나 권한이 필요한 주소 요청입니다");
		
		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader: " +jwtHeader);
		
		// header가 있는지 확인
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			System.out.println("no header");
			chain.doFilter(request, response); // 다음 필터로
			return;
		}
		// JWT 토큰을 검증해서 정상적인 사용자인지 확인
		String token = request.getHeader("Authorization")
								.replace("Bearer ", "");
		System.out.println("token : " + token);
		String username = 
				JWT.require(Algorithm.HMAC512("cos"))
				.build().verify(token).
				getClaim("username").asString();
		
		//서명이 정상적으로 됨
		if(username!=null) {
			User userEntity = userRepository.findByUsername(username);
			System.out.println("username : " +username);
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			
			// Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken(
							principalDetails, 
							null, 
							principalDetails.getAuthorities());
			// 강제로 시큐리티의 세션에 접근해서 Authentication 객체를 저장
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}
}
