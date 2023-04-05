package org.jangel.capacityservice.model;

import java.util.List;

import lombok.Data;

@Data
public class Member {
	private int id;
	private String name;
	private List<Role> roles;
}
