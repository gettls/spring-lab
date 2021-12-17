package hello.crud_ex1.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
public class HomeController {

	@GetMapping("/")
	public String home(@Login Member loginMember, Model model) {
		if(loginMember==null) {
			return "home";
		}
		model.addAttribute("member",loginMember);
		return "login/loginHome";
	}
	
}
