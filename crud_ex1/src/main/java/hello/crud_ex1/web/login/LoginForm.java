package hello.crud_ex1.web.login;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginForm {
	@NotEmpty(message = "��й�ȣ�� �Է����ּ���")
	public String password;
	@NotEmpty(message = "���̵� �Է����ּ���")
	public String loginId;
}
