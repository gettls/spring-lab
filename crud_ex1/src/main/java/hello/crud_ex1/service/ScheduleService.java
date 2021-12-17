package hello.crud_ex1.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.domain.Schedule;
import hello.crud_ex1.repository.MemberRepository;
import hello.crud_ex1.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository scheduleRepository;
	private final MemberRepository memberRepository;
	
	@Transactional
	public Long add(Schedule schedule) {
		scheduleRepository.save(schedule);
		return schedule.getId();
	}
	
	@Transactional
	public Long update(Long id, Schedule updateSchedule) {
		Schedule schedule = scheduleRepository.findOne(id);
		schedule.setDate(updateSchedule.getDate());
		schedule.setName(updateSchedule.getName());
		scheduleRepository.save(schedule);
		return schedule.getId();
	}
	
	public List<Schedule> findByMember(Member member){
		return scheduleRepository.findByMember(member);
	}
	
	public Schedule findById(Long id) {
		return scheduleRepository.findOne(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		scheduleRepository.delete(id);
	}
}
