package org.jangel.scrumteam.controller;

import java.util.List;
import java.util.concurrent.TimeoutException;

import org.jangel.scrumteam.exception.MemberNotFoundException;
import org.jangel.scrumteam.model.Member;
import org.jangel.scrumteam.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/v1/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@Value("${enable.random.fail}")
	private boolean randomFail;

	@GetMapping
	public ResponseEntity<List<Member>> getMembers() {
		return ResponseEntity.ok(memberService.getMembers());
	}

	@GetMapping("{memberId}")
	@CircuitBreaker(name = "memberService")
	public ResponseEntity<Member> getMember(@PathVariable("memberId") int memberId)
			throws MemberNotFoundException, TimeoutException {
		Member member = memberService.getMember(memberId);

		if (randomFail && Math.random() > .5) {
			try {
				Thread.sleep(3000);
				throw new TimeoutException();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ResponseEntity.ok(member);
	}
}
