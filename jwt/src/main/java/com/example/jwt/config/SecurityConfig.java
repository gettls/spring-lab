package com.example.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.example.jwt.config.jwt.JwtAuthenticationFilter;
import com.example.jwt.config.jwt.JwtAuthorizationFilter;
import com.example.jwt.filter.MyFilter1;
import com.example.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final CorsConfig corsConfig;
	private final UserRepository userRepository;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.addFilterBefore(new Filter1(),BasicAuthenticationFilter.class);
//		// BasicAuthenticationFilter 가 만들어지기 전에 MyFilter를 걸어줌 
//		http.csrf().disable();
//		// 세션을 사용하지 않겠다
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.addFilter(corsFilter) // @CrossOrigin(인증X), 시큐리티 필터에 등록 인증(O)
//		.formLogin().disable() // JWT를 사용하기 때문에 LoginForm 필요 없음
//		.httpBasic().disable() // Http Basic Athorization
//		.addFilter(new JwtAuthenticationFilter(authenticationManager())) //AuthenticationManager 가 login을 진행해줌
//		.addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
//		.authorizeRequests()   // -> resource에 접근할 때 브라우저가 username,password를 확인해 인가를 제한하는 방법
//		.antMatchers("/api/v1/user/**")
//		.hasAnyRole("ROLE_USER", "ROLE_MANAGER", "ROLE_ADMIN")
//		.antMatchers("/api/v1/manager/**")
//		.hasAnyRole("ROLE_MANAGER", "ROLE_ADMIN")
//		.antMatchers("/api/v1/admin/**")
//		.hasAnyRole("ROLE_ADMIN")
//		.anyRequest().permitAll();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilter(corsConfig.corsFilter())
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.formLogin().disable()
				.httpBasic().disable()
				
				.addFilter(new JwtAuthenticationFilter(authenticationManager()))
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
				.authorizeRequests()
				.antMatchers("/api/v1/user/**")
				.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/api/v1/manager/**")
					.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
				.antMatchers("/api/v1/admin/**")
					.access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll();
	}
}
