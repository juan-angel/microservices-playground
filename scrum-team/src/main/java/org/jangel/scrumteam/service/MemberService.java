package org.jangel.scrumteam.service;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.jangel.scrumteam.exception.MemberNotFoundException;
import org.jangel.scrumteam.model.Member;
import org.jangel.scrumteam.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	@Value("${enable.random.fail}")
	private boolean randomFail;

	public List<Member> getMembers() {
		return memberRepository.findAll();
	}

	@CircuitBreaker(name = "memberService")
	public Member getMember(int id) throws MemberNotFoundException, TimeoutException {
		Member member = null;

		if (randomFail && Math.random() > .0001) {
			try {
				Thread.sleep(3000);
				throw new TimeoutException();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		member = memberRepository.findById(id).orElse(null);

		if (member == null) {
			throw new MemberNotFoundException(id);
		}

		return member;
	}
}
