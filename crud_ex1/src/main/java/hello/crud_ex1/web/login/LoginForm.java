package hello.crud_ex1.web.login;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {
	@NotEmpty(message = "비밀번호를 입력해주세요")
	public String password;
	@NotEmpty(message = "아이디를 입력해주세요")
	public String loginId;
}
