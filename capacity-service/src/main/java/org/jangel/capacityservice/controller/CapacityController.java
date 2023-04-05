package org.jangel.capacityservice.controller;

import org.jangel.capacityservice.dto.MemberCapacityDTO;
import org.jangel.capacityservice.member.service.client.MemberDiscoveryClient;
import org.jangel.capacityservice.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/capacity")
public class CapacityController {
	@Autowired
	private MemberDiscoveryClient client;
	
	@GetMapping("{memberId}")
	public ResponseEntity<MemberCapacityDTO> getMemberCapacity(@PathVariable("memberId") int memberId) {
		Member member = client.getMemberCapacity(memberId);
		MemberCapacityDTO dto = new MemberCapacityDTO();
		dto.setMember(member);
		dto.setStoryPointsCapacity(8);
		
		return ResponseEntity.ok(dto);
	}
}
