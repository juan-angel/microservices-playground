package org.jangel.capacityservice.controller;

import org.jangel.capacityservice.dto.MemberCapacityDTO;
import org.jangel.capacityservice.member.service.client.MemberDiscoveryClient;
import org.jangel.capacityservice.member.service.client.MemberRestTemplateClient;
import org.jangel.capacityservice.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/capacity")
public class CapacityController {
	private static final int CLIENT_TYPE_DISCOVERY_CLIENT = 1;
	private static final int CLIENT_TYPE_LOAD_BALANCE_AWARE_REST_TEMPLATE = 2;
	
	@Autowired
	private MemberDiscoveryClient client;

	@Autowired
	private MemberRestTemplateClient memberRestTemplateClient;
	
	@GetMapping(value = "{memberId}", params = "clientType")
	public ResponseEntity<MemberCapacityDTO> getMemberCapacity(@PathVariable("memberId") int memberId,
			@RequestParam("clientType") int clientType) {
		Member member = null;

		switch (clientType) {
		case CapacityController.CLIENT_TYPE_DISCOVERY_CLIENT:
			member = client.getMemberCapacity(memberId);
			break;
		case CapacityController.CLIENT_TYPE_LOAD_BALANCE_AWARE_REST_TEMPLATE:
			member = memberRestTemplateClient.getMember(memberId);
			break;
		}
		
		MemberCapacityDTO dto = new MemberCapacityDTO();
		dto.setMember(member);
		dto.setStoryPointsCapacity(8);
		
		return ResponseEntity.ok(dto);
	}
}
