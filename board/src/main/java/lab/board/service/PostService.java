package lab.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import lab.board.domain.Post;
import lab.board.form.PostDto;
import lab.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
	
	private final PostRepository postRepository;
	
	@Transactional
	public Post save(PostDto form) {
		Post post = Post.builder()
				.title(form.getTitle())
				.contents(form.getContents())
				.build();
		postRepository.save(post);
		return post;
	}
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	public Post findById(Long id) {
		return postRepository.findById(id);
	}
}