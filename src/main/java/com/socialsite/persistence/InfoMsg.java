
package com.socialsite.persistence;

public class InfoMsg extends Message
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;
	/** The sender who sends the friend request */
	private User Sender;

	public InfoMsg()
	{
	}

	public String getMessage()
	{
		return message;
	}

	public User getSender()
	{
		return Sender;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setSender(final User sender)
	{
		Sender = sender;
	}

}
