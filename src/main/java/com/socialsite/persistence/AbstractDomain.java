
package com.socialsite.persistence;

import java.io.Serializable;


public interface AbstractDomain extends Serializable
{

	/**
	 * generalized method to get the id of the Domain object
	 * 
	 * @return id of the Domain object
	 */
	public long getId();
}
