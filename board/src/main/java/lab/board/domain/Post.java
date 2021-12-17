package lab.board.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
public class Post extends BaseEntity{
	
	@Id @GeneratedValue
	private Long id;
	private String title;
	private String contents;
	protected Post() {
	}

	@Builder
	public Post(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
}	
