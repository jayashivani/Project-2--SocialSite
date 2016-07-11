
package com.socialsite.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Answer implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	/** The user who answered the question */
	private User user;
	private Question question;
	private Set<Comment> comments = new HashSet<Comment>();
	private Date time;
	private String text;

	public Answer()
	{
	}

	public Answer(final Question question, final User user)
	{
		setQuestion(question);
		setUser(user);
		setTime(new Date());
	}

	public void addComment(final Comment comment)
	{
		getComments().add(comment);
		comment.setAnswer(this);
	}

	public Set<Comment> getComments()
	{
		return comments;
	}

	public long getId()
	{
		return id;
	}

	public Question getQuestion()
	{
		return question;
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

	public void removeComment(final Comment comment)
	{
		getComments().remove(comment);
	}

	public void setComments(final Set<Comment> comments)
	{
		this.comments = comments;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setQuestion(final Question question)
	{
		this.question = question;
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
