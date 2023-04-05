package org.jangel.capacityservice.controller;

import org.jangel.capacityservice.dto.MemberCapacityDTO;
import org.jangel.capacityservice.member.service.client.MemberDiscoveryClient;
import org.jangel.capacityservice.member.service.client.MemberFeignClient;
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
	
	private static final String DISCOVERY_CLIENT = "DISCOVERY_CLIENT";
	private static final String LOAD_BALANCE_AWARE_REST_TEMPLATE = "LOAD_BALANCE_AWARE_REST_TEMPLATE";
	private static final String FEIGN_CLIENT = "FEIGN_CLIENT";

	@Autowired
	private MemberDiscoveryClient discoveryClient;

	@Autowired
	private MemberRestTemplateClient memberRestTemplateClient;
	
	@Autowired
	private MemberFeignClient feignClient;

	@GetMapping(value = "{memberId}", params = "clientType")
	public ResponseEntity<MemberCapacityDTO> getMemberCapacity(@PathVariable("memberId") int memberId,
			@RequestParam("clientType") int clientType) {
		MemberCapacityDTO dto = new MemberCapacityDTO();
		Member member = null;

		switch (clientType) {
		case CapacityController.CLIENT_TYPE_DISCOVERY_CLIENT:
			member = discoveryClient.getMemberCapacity(memberId);
			dto.setDiscoveryMethod(CapacityController.DISCOVERY_CLIENT);
			break;
		case CapacityController.CLIENT_TYPE_LOAD_BALANCE_AWARE_REST_TEMPLATE:
			member = memberRestTemplateClient.getMember(memberId);
			dto.setDiscoveryMethod(CapacityController.LOAD_BALANCE_AWARE_REST_TEMPLATE);
			break;
		default: // Feign client. Similar to CLIENT_TYPE_LOAD_BALANCE_AWARE_REST_TEMPLATE
			member = feignClient.getMember(memberId);
			dto.setDiscoveryMethod(CapacityController.FEIGN_CLIENT);
		}

		dto.setMember(member);
		dto.setStoryPointsCapacity(8);

		return ResponseEntity.ok(dto);
	}
}
