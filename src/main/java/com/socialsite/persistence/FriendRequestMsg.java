
package com.socialsite.persistence;


public class FriendRequestMsg extends Message
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The sender who sends the friend request */
	private User sender;
	/** message send during friend request */
	private String message;

	public FriendRequestMsg()
	{
	}

	public String getMessage()
	{
		return message;
	}

	public User getSender()
	{
		return sender;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setSender(final User user)
	{
		sender = user;
	}
}
