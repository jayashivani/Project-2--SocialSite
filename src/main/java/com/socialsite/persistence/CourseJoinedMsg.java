
package com.socialsite.persistence;

public class CourseJoinedMsg extends Message
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User sender;
	private Course course;

	public CourseJoinedMsg()
	{
	}

	public Course getCourse()
	{
		return course;
	}

	public User getSender()
	{
		return sender;
	}

	public void setCourse(final Course course)
	{
		this.course = course;
	}

	public void setSender(final User sender)
	{
		this.sender = sender;
	}

}
