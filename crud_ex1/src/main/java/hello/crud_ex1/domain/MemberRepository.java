package hello.crud_ex1.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

//@Repository
public class MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}
	
	public Member findById(Long id) {
		return store.get(id);
	}
	
	
	public List<Member> findAll(){
		return new ArrayList<Member>(store.values());
	}
	
	public Optional<Member> findByLoginId(String loginId) {
		return findAll().stream()
				.filter(m->m.getLoginId().equals(loginId))
				.findFirst();
	}
	
	public void updateMember(Long id, Member updateMember) {
		Member findOne = findById(id);
		findOne.setLoginId(updateMember.getLoginId());
		findOne.setName(updateMember.getName());
		findOne.setPassword(updateMember.getPassword());
	}
	
	public void clear() {
		store.clear();
	}
}
