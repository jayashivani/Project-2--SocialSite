
package com.socialsite.entitymodel;

import java.io.Serializable;


public class StringWrapper implements Serializable
{

	private static final long serialVersionUID = 1L;
	/** holds the model value */
	private String model;

	/**
	 * constructor
	 */
	public StringWrapper()
	{
		// initialize with empty strings
		model = "";
	}

	/**
	 * constructor
	 * 
	 * @param model
	 *            string model
	 */
	public StringWrapper(final String model)
	{
		this.model = model;
	}

	/**
	 * getter
	 * 
	 * @return model string
	 */
	public String getModel()
	{
		return model;
	}

	/**
	 * setter
	 * 
	 * @param model
	 *            string model
	 */
	public void setModel(final String model)
	{
		this.model = model;
	}

	/**
	 * @return model string
	 */
	@Override
	public String toString()
	{
		return model;
	}
}
