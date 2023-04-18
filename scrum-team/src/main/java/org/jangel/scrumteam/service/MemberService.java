package org.jangel.scrumteam.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.jangel.scrumteam.exception.MemberNotFoundException;
import org.jangel.scrumteam.model.Member;
import org.jangel.scrumteam.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	@Value("${enable.random.fail}")
	private boolean randomFail;

	@CircuitBreaker(name = "memberService", fallbackMethod = "buildDefaultList")
	@Bulkhead(name = "bulkheadMemberService", fallbackMethod = "buildDefaultList")
	@Retry(name = "retryMemberService", fallbackMethod = "buildDefaultList")
	public List<Member> getMembers() throws TimeoutException {
		if (randomFail && Math.random() > .5) {
			try {
				Thread.sleep(3000);
				throw new TimeoutException();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return memberRepository.findAll();
	}

	@SuppressWarnings("unused")
	private List<Member> buildDefaultList(Throwable t) {
		Member m = new Member();
		m.setName("Unable to connect to database");

		return Arrays.asList(m);
	}

	@CircuitBreaker(name = "memberService")
	public Member getMember(int id) throws MemberNotFoundException, TimeoutException {
		Member member = null;

		if (randomFail && Math.random() > .5) {
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
