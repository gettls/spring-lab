package lab.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
public class Post extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name = "POST_ID")
	private Long id;
	private String title;
	private String contents;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id") // FK->PK
	private Member member;
	
	protected Post() {
	}

	@Builder
	public Post(String title, String contents, Member member) {
		this.title = title;
		this.contents = contents;
		this.member = member;
	}
	
	public void ChangeContents(String contents) {
		this.contents = contents;
	}
	public void ChangeTitle(String title) {
		this.title = title;
	}
}	
