
package com.socialsite.email;

import java.util.ArrayList;
import java.util.List;


public class Email
{

	private String sender;
	private List<String> receivers = new ArrayList<String>();
	private String message;
	private String subject;

	public Email()
	{
	}

	public void addReceivers(final String receiver)
	{
		receivers.add(receiver);
	}

	public String getMessage()
	{
		return message;
	}

	public List<String> getReceivers()
	{
		return receivers;
	}


	public String getSender()
	{
		return sender;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setMessage(final String message)
	{
		this.message = message;
	}

	public void setReceivers(final List<String> receivers)
	{
		this.receivers = receivers;
	}

	public void setSender(final String sender)
	{
		this.sender = sender;
	}

	public void setSubject(final String subject)
	{
		this.subject = subject;
	}
}
