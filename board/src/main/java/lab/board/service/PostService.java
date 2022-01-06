package lab.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import lab.board.domain.Member;
import lab.board.domain.Post;
import lab.board.form.PostDto;
import lab.board.repository.MemberRepository;
import lab.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
	
	private final PostRepository postRepository;
	private final MemberRepository memberRepository;
	
	@Transactional
	public void save(PostDto form, String username) {
		Member member = memberRepository.findByUsername(username);
		Post post = Post.builder()
						.contents(form.getContents())
						.title(form.getTitle())
						.member(member)
						.build();
		postRepository.save(post);
	}
	
	@Transactional
	public void edit(PostDto form, Long id) {
		Post post = postRepository.findById(id).get();
		post.ChangeContents(form.getContents());
		post.ChangeTitle(form.getTitle());
	}
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	public Optional<Post> findById(Long id) {
		return postRepository.findById(id);
	}
	
	public void delete(Long id) {
		postRepository.deleteById(id);
	}
	
	// paging
	public Page<Post> list(Pageable pageable){
		return postRepository.findAll(pageable);
	}
}
