
package com.socialsite.persistence;

import java.util.Date;

public class Comment implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private Answer answer;
	/** The user who comments the answer */
	private User user;
	private Date time;
	private String text;

	public Comment()
	{
		// TODO Auto-generated constructor stub
	}

	public Answer getAnswer()
	{
		return answer;
	}

	public long getId()
	{
		return id;
	}

	public String getText()
	{
		return text;
	}

	public Date getTime()
	{
		return time;
	}

	public User getUser()
	{
		return user;
	}

	public void setAnswer(final Answer answer)
	{
		this.answer = answer;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setText(final String text)
	{
		this.text = text;
	}

	public void setTime(final Date time)
	{
		this.time = time;
	}

	public void setUser(final User user)
	{
		this.user = user;
	}
}
