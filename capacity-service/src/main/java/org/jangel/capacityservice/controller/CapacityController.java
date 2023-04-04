package org.jangel.capacityservice.controller;

import java.util.List;

import org.jangel.capacityservice.dto.MemberCapacityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;

@RestController
@RequestMapping("/v1/capacity")
public class CapacityController {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping("{memberId}")
	public ResponseEntity<MemberCapacityDTO> getMemberCapacity(@PathVariable("memberId") int memberId) {
		RestTemplate restTemplate = new RestTemplate();
		List<InstanceInfo> instances = discoveryClient.getInstancesById("scrum-team");

		return null;
	}
}
