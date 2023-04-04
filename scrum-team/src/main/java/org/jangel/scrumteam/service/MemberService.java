package org.jangel.scrumteam.service;

import java.util.List;

import org.jangel.scrumteam.exception.MemberNotFoundException;
import org.jangel.scrumteam.model.Member;
import org.jangel.scrumteam.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> getMembers() {
		return memberRepository.findAll();
	}
	
	public Member getMember(int id) throws MemberNotFoundException {
		Member member = memberRepository.findById(id).orElse(null);
		
		if (member == null) {
			throw new MemberNotFoundException(id);
		}
		
		return member;
	}
}
