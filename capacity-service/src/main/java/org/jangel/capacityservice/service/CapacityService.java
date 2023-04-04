package org.jangel.capacityservice.service;

import org.jangel.capacityservice.dto.MemberCapacityDTO;
import org.jangel.capacityservice.exception.MemberNotFoundException;
import org.jangel.scrumteam.model.Member;
import org.jangel.scrumteam.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CapacityService {
	@Autowired
	private MemberService memberService;
	
	public MemberCapacityDTO calculateMemberCapacity(int idMember) throws MemberNotFoundException {
		Member member = memberService.getMember(idMember);
		MemberCapacityDTO result = null;
		
		if (member == null) {
			throw new MemberNotFoundException(idMember);
		} else {
			result = new MemberCapacityDTO();
			
			result.setMember(member);
			result.setStoryPointsCapacity(8);
		}
		
		return result;
	}
}
