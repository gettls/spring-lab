package hello.crud_ex1.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.Schedule;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ScheduleRepository {
	
	private final EntityManager em;
	
	public void save(Schedule schedule) {
		log.info("add schedule");
		em.persist(schedule);
	}
	
	public Schedule findOne(Long id) {
		return em.find(Schedule.class, id);
	}
	
	public List<Schedule> findAll(){
		return em.createQuery("select s from Schedule s", Schedule.class)
				.getResultList();
	}
	
	public void delete(Long id) {
		log.info("delete from repo");
		em.createQuery("delete from Schedule s where s.id = :id").setParameter("id", id).executeUpdate();
	}
	
	public List<Schedule> findByMember(Member member){
		return em.createQuery("select s from Schedule s where s.member = :member", Schedule.class)
				.setParameter("member", member)
				.getResultList();
	}
}
