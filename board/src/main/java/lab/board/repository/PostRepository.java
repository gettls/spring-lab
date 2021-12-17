package lab.board.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lab.board.domain.Post;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostRepository {

	private final EntityManager em;
	
	public void save(Post post) {
		em.persist(post);
	}
	
	public List<Post> findAll(){
		return em.createQuery("select p from Post p", Post.class)
				.getResultList();
	}
	
	public Post findById(Long id) {
		return em.find(Post.class, id);
	}
}
