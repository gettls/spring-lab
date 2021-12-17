package hello.crud_ex1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.crud_ex1.domain.Member;
import hello.crud_ex1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Transactional
	public Long save(Member member) {
		log.info("member = {} ", member.getName());
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		Optional<Member> findMember = Optional.ofNullable(memberRepository.findByLoginId(member.getLoginId()));
		log.info("findMember ={}",findMember);
		if(!findMember.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다");
		}
	}
	
	public Member findOne(Long id) {
		return memberRepository.findOne(id);
	}
	
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Member findOnebyLoginId(String loginId) {
		return memberRepository.findByLoginId(loginId);
	}
	
	@Transactional
	public void updateMember(Long id, Member updateMember) {
		Member findOne = memberRepository.findOne(id);
		findOne.setLoginId(updateMember.getLoginId());
		findOne.setName(updateMember.getName());
		findOne.setPassword(updateMember.getPassword());
	}
	
}
