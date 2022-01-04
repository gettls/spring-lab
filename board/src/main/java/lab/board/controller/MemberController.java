package lab.board.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lab.board.domain.Member;
import lab.board.dto.MemberAddDto;
import lab.board.dto.MemberLoginDto;
import lab.board.repository.MemberRepository;
import lab.board.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("register")
	public String register(Model model) {
		MemberAddDto memberDto = new MemberAddDto();
		model.addAttribute("memberDto", memberDto);
		return "register";
	}
	
	@GetMapping("login")
	public String loginForm(Model model) {
		MemberLoginDto memberLoginDto = new MemberLoginDto();
		model.addAttribute("memberLoginDto", memberLoginDto);
		return "loginForm";
	}
	
	@PostMapping("login")
	public String login(MemberLoginDto memberLoginDto) {
		System.out.println("memberLoginDto :"+memberLoginDto);
		return null;
	}
	
	@PostMapping("register")
	public String add(MemberAddDto memberDto) {
		memberService.register(memberDto);
		return "redirect:/";
	}
}
