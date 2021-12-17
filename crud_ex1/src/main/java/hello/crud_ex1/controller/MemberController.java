package hello.crud_ex1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.MemberRepository;
import hello.crud_ex1.service.MemberService;
import hello.crud_ex1.web.schedule.form.MemberAddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
@Slf4j
public class MemberController {
	
	private final MemberService memberService;
	
	@GetMapping("/add")
	public String member(@ModelAttribute Member member) {
		return "members/MemberAddForm"; 
	}
	
	@PostMapping("/add")
	public String save(@Validated @ModelAttribute("member") MemberAddForm form, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			log.info("error = {}", bindingResult.getAllErrors());
			return "members/MemberAddForm";
		}
		Member member = new Member();
		member.setLoginId(form.getLoginId());
		member.setName(form.getName());
		member.setPassword(form.getPassword());
		memberService.save(member);
		
		return "redirect:/";
	}
}
