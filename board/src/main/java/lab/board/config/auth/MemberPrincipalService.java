package lab.board.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lab.board.domain.Member;
import lab.board.repository.MemberRepository;

@Service
public class MemberPrincipalService implements UserDetailsService{

	@Autowired private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepository.findByUsername(username);
		if(member == null) return null;
		return new MemberPrincipal(member);
	}
	
}
