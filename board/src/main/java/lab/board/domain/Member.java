package lab.board.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lab.board.dto.MemberAddDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class Member {

	@Id @GeneratedValue
	@Column(name = "MEMBER_ID")
	private Long id;
	private String username;
	private String password;
	@OneToMany(mappedBy = "member")
	private List<Post> posts;
	
	protected Member() {
	}

	@Builder
	public Member(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Member(MemberAddDto memberDto) {
		this.username = memberDto.getUsername();
		this.password = memberDto.getPassword();
	}
}