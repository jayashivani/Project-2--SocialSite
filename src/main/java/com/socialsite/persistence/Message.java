
package com.socialsite.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public abstract class Message implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private Date time;

	private Set<User> users = new HashSet<User>();

	public Message()
	{
		// set the time
		setTime(new Date());
	}

	/*
	 * add the user to the receiver list
	 */
	public void addUser(final User user)
	{
		getUsers().add(user);
	}

	public void removeUser(final User user)
	{
		getUsers().remove(user);
	}

	public long getId()
	{
		return id;
	}

	public Date getTime()
	{
		return time;
	}

	public Set<User> getUsers()
	{
		return users;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setTime(final Date time)
	{
		this.time = time;
	}

	public void setUsers(final Set<User> users)
	{
		this.users = users;
	}
}
