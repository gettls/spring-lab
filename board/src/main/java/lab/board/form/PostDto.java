package lab.board.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lab.board.domain.Member;
import lombok.Data;

@Data
public class PostDto {

	@NotBlank
	private String title;
	@NotEmpty
	private String contents;
	@DateTimeFormat
	private LocalDateTime createTime;
	private Member member;
}
