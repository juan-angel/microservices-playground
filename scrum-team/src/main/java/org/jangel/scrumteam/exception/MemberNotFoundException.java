package org.jangel.scrumteam.exception;

public class MemberNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;

	public MemberNotFoundException(int id) {
		this.id = id;
	}

	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder();
		message.append("Member with id ").append(id).append(" does not exist");

		return message.toString();
	}
}
