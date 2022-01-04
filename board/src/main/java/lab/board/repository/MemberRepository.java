package lab.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lab.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	public Member findByUsername(String username); 
}
