package lab.board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lab.board.config.auth.MemberPrincipal;
import lab.board.domain.Member;
import lab.board.domain.Post;
import lab.board.form.PostDto;
import lab.board.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class PostController {

	private final PostService postService;
	
	@GetMapping()
	public String board(Model model) {
		List<Post> post = postService.findAll();
		model.addAttribute("post", post);
		return "board";
	}
	
	@GetMapping("/create")
	public String addForm(@ModelAttribute Post post) {
		return "create";
	}
	
	@PostMapping("/create")
	public String add(@Validated @ModelAttribute("post") PostDto form,
			@AuthenticationPrincipal MemberPrincipal memberPrincipal,
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "board";
		}
		postService.save(form, memberPrincipal.getUsername());
		List<Post> post = postService.findAll();
		model.addAttribute("post", post);
		return "redirect:/board";
	}
	
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		Post post = postService.findById(id).get();
		model.addAttribute("post",post);
		return "edit";
	}
	
	@PostMapping("/edit/{id}")
	public String edit(@Validated @ModelAttribute PostDto form,
			@PathVariable Long id) {
		postService.edit(form, id);
		return "redirect:/board";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		postService.delete(id);
		return "redirect:/board";
	}
}
