package lab.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public BCryptPasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/board/create").authenticated()
			.antMatchers("/board/edit").authenticated()
			.and()
			.formLogin()
			.loginPage("/member/login")
			.loginProcessingUrl("/member/login")
			.defaultSuccessUrl("/")
			.and()
			.logout()
			.logoutUrl("/member/logout")
			.logoutSuccessUrl("/member/login");
	}
}
