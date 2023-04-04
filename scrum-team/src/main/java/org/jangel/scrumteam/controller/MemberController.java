package org.jangel.scrumteam.controller;

import java.util.List;

import org.jangel.scrumteam.model.Member;
import org.jangel.scrumteam.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/member")
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping
	public ResponseEntity<List<Member>> getMembers() {
		return ResponseEntity.ok(memberService.getMembers());
	}
}