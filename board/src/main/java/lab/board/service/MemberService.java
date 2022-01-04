package lab.board.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lab.board.domain.Member;
import lab.board.dto.MemberAddDto;
import lab.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final MemberRepository memberRepository;
	
	public void register(MemberAddDto memberAddDto) {
		memberAddDto.setPassword(bCryptPasswordEncoder.encode(memberAddDto.getPassword()));
		Member member = new Member(memberAddDto);
		memberRepository.save(member);
	}
}
