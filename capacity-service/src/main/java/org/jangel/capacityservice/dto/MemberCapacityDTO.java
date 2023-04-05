package org.jangel.capacityservice.dto;


import org.jangel.capacityservice.model.Member;

import lombok.Data;

@Data
public class MemberCapacityDTO {
	private Member member;
	private double storyPointsCapacity;
}
