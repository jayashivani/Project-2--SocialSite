
package com.socialsite.persistence;

public class Admin extends User
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private University university;

	public Admin()
	{
	}

	public Admin(final String userName, final String password)
	{
		super(userName, password);
	}

	public University getUniversity()
	{
		return university;
	}

	public void setUniversity(final University university)
	{
		this.university = university;
	}
}
