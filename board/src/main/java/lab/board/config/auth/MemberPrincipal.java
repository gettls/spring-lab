package lab.board.config.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lab.board.domain.Member;
import lombok.Getter;

@Getter
public class MemberPrincipal implements UserDetails{
	
	private Member member;
	
	public MemberPrincipal(Member member) {
		this.member=member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return member.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
