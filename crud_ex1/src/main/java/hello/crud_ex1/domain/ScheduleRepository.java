package hello.crud_ex1.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

//@Repository
public class ScheduleRepository {

	private static Map<Long, Schedule> store = new HashMap<>();
	private static long sequence = 0L;
	
	public Schedule save(Schedule schedule, Member member) {
		schedule.setId(++sequence);
		store.put(sequence, schedule);
		return schedule;
	}
	
	public Schedule findById(Long id) {
		return store.get(id);
	}
	
	public List<Schedule> findByMember(Member member) {
		return (List<Schedule>) findAll().stream()
				.filter(m->m.getMember().equals(member))
				.collect(Collectors.toList());
	}
	
	public List<Schedule> findAll(){
		return new ArrayList<>(store.values());
	}
	
	public void updateSchedule(Long id, Schedule updateParam) {
		Schedule findOne = findById(id);
		findOne.setDate(updateParam.getDate());
		findOne.setName(updateParam.getName());
	}
	
	
	public void clear() {
		store.clear();
	}
}
