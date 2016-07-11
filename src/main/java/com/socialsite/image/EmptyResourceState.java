
package com.socialsite.image;

import org.apache.wicket.markup.html.DynamicWebResource.ResourceState;

/**
 * a empty resource state used to indicate that the requested resource can't be
 * obtained
 * 

 * 
 */
public class EmptyResourceState extends ResourceState
{
	@Override
	public String getContentType()
	{
		return null;
	}

	@Override
	public byte[] getData()
	{
		return null;
	}
}
