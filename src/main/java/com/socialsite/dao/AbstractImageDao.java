
package com.socialsite.dao;

import java.util.Date;


public interface AbstractImageDao<T> extends AbstractDao<T>
{
	/**
	 * gets the image
	 * 
	 * @param id
	 *            id of the domain class
	 * @return image
	 */
	public byte[] getImage(final long id);

	/**
	 * gets the last modified date
	 * 
	 * @param id
	 *            id of the domain class
	 * @return last modified date
	 */
	public Date getLastModifiedTime(final long id);

	/**
	 * gets the thumb
	 * 
	 * @param id
	 *            id of the domain class
	 * @return thumb
	 */
	public byte[] getThumb(final long id);
}
