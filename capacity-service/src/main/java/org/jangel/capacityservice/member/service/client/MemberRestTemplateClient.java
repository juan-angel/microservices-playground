package org.jangel.capacityservice.member.service.client;

import org.jangel.capacityservice.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MemberRestTemplateClient {
	@Autowired
	private RestTemplate restTemplate;

	public Member getMember(int memberId) {
		ResponseEntity<Member> restExchange = restTemplate.exchange("http://scrum-team/v1/member/{memberId}",
				HttpMethod.GET, null, Member.class, memberId);
		
		return restExchange.getBody();
	}
}
