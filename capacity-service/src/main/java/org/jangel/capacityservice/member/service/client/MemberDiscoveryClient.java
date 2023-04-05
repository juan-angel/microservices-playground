package org.jangel.capacityservice.member.service.client;

import java.net.URI;
import java.util.List;

import org.jangel.capacityservice.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;


@Component
public class MemberDiscoveryClient {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public Member getMemberCapacity(int idMember) {
		RestTemplate restTemplate = new RestTemplate();
		List<ServiceInstance> instances = discoveryClient.getInstances("scrum-team");
		Member result = null;
		
		if (!CollectionUtils.isEmpty(instances)) {
			URI uri = instances.get(0).getUri();
			
			
			String serviceUri = String.format("%s/v1/member/%d", uri.toString(), idMember);
			ResponseEntity<Member> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null,
					Member.class, idMember);
			
			result = restExchange.getBody();
		}
		
		return result;
	}
}
