
package com.socialsite.persistence;

public class StaffRequestMsg extends Message
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The sender who sends the staff request message */
	private Staff sender;
	private University university;

	public StaffRequestMsg()
	{
	}

	public StaffRequestMsg(final Staff sender)
	{
		this.sender = sender;
	}

	public Staff getSender()
	{
		return sender;
	}

	public University getUniversity()
	{
		return university;
	}

	public void setSender(final Staff sender)
	{
		this.sender = sender;
	}

	public void setUniversity(final University university)
	{
		this.university = university;
	}

}
