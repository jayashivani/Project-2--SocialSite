

package com.socialsite.util;

import java.io.Serializable;

import com.socialsite.profile.Access;


public class PrivacyModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	private Access privacy;


	public PrivacyModel()
	{
	}

	public PrivacyModel(final String value, final Access privacy)
	{
		this.value = value;
		this.privacy = privacy;
	}

	public Access getPrivacy()
	{
		return privacy;
	}

	public String getValue()
	{
		return value;
	}

	public void setPrivacy(final Access privacy)
	{
		this.privacy = privacy;
	}

	public void setValue(final String value)
	{
		this.value = value;
	}
}
