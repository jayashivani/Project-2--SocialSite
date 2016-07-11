
package com.socialsite.persistence;


public class Activation implements AbstractDomain
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private Admin admin;
	private String universityName;

	public Activation()
	{
	}

	public Admin getAdmin()
	{
		return admin;
	}

	public long getId()
	{
		return id;
	}

	public String getUniversityName()
	{
		return universityName;
	}

	public void setAdmin(final Admin admin)
	{
		this.admin = admin;
	}

	public void setId(final long id)
	{
		this.id = id;
	}

	public void setUniversityName(final String universityName)
	{
		this.universityName = universityName;
	}

}
