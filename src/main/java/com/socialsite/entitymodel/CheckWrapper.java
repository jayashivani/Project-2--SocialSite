
package com.socialsite.entitymodel;

import java.io.Serializable;


public class CheckWrapper<T> implements Serializable
{

	private static final long serialVersionUID = 1L;
	/** wrapper for the business object */
	private T name;
	/** boolean to hold the checkbox value */
	private Boolean selected = Boolean.FALSE;

	/**
	 * constructor
	 * 
	 * @param wrapped
	 *            object to be wrapped
	 */
	public CheckWrapper(final T wrapped)
	{
		this.name = wrapped;
	}

	/**
	 * getter
	 * 
	 * @return the name wrapped in the object
	 */
	public T getName()
	{
		return name;
	}

	/**
	 * getter
	 * 
	 * @return the boolean contains the state of the checkbox
	 */
	public Boolean getSelected()
	{
		return selected;
	}

	/**
	 * setter
	 * 
	 * @param wrapped
	 *            name to be wrapped in the object
	 */
	public void setName(final T wrapped)
	{
		this.name = wrapped;
	}

	/**
	 * setter
	 * 
	 * @param selected
	 *            checkbox value
	 */
	public void setSelected(final Boolean selected)
	{
		this.selected = selected;
	}

	/**
	 * 
	 * @return name and the checkbox value seperated by a colon
	 */
	@Override
	public String toString()
	{
		return name.toString() + ":" + selected;
	}
}
