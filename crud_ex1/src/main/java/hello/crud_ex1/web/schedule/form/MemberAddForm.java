package hello.crud_ex1.web.schedule.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import hello.crud_ex1.web.validation.Name;
import lombok.Data;

@Data
public class MemberAddForm {
	@Name
	@NotBlank
	private String name;
	@NotBlank
	private String loginId;
	@NotBlank
	private String password;
}
