package org.jangel.capacityservice.member.service.client;

import org.jangel.capacityservice.model.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("scrum-team")
public interface MemberFeignClient {
	@GetMapping(value = "v1/member/{memberId}", consumes = "application/json")
	Member getMember(@PathVariable("memberId") int memberId);
}
