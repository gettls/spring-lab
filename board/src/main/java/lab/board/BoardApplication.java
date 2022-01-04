package lab.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lab.board.config.auth.MemberPrincipal;
import lab.board.domain.Member;


@SpringBootApplication
@EnableJpaAuditing
public class BoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}
	
	@Bean
	public AuditorAware<String> auditorProvider(){
		return () ->{
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication==null || !authentication.isAuthenticated()) {
				return null;
			}
			System.out.println("authentication = "+authentication.getPrincipal());
			MemberPrincipal memberPrincipal = (MemberPrincipal)authentication.getPrincipal();
			return Optional.of(memberPrincipal.getMember().getUsername());
		};
	}
}
