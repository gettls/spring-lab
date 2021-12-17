package hello.crud_ex1.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.Schedule;
import hello.crud_ex1.web.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {
	
	private final EntityManager em;
	
	public void save(Member member) {
		log.info("repo save");
		em.persist(member);
	}
	
	public Member findOne(Long id) {
		log.info("repo findById");
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
	
	public Member findByLoginId(String loginId){
		log.info("findByLoginId");
		return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
				.setParameter("loginId", loginId)
				.getResultStream() // method to return null of single result 
				.findFirst()
				.orElse(null);
	}
	
	public List<Schedule> findSchedule(Long id){
		return em.createQuery(
				"select s from Schedule s"
				+ "fetch join s.member m"
				+ "where m.id = :id", Schedule.class)
				.setParameter("id", id)
				.getResultList();
	}		
}
