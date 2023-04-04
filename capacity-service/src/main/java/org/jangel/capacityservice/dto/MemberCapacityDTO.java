package org.jangel.capacityservice.dto;

import org.jangel.scrumteam.model.Member;

import lombok.Data;

@Data
public class MemberCapacityDTO {
	private Member member;
	private double storyPointsCapacity;
}
