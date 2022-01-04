package lab.board.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lab.board.domain.Post;
import lombok.RequiredArgsConstructor;

public interface PostRepository extends JpaRepository<Post, Long>{

}
