
package com.socialsite.util;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import com.socialsite.persistence.User;
import com.socialsite.user.AllUsersPage;


public class ShowAllLink extends Link<Void>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final IDataProvider<User> dataProvider;
	int size;
	Class<? extends Page> pageClazz = null;

	public ShowAllLink(final String id, final IDataProvider<User> dataProvider)
	{
		super(id);
		this.dataProvider = dataProvider;
		if (dataProvider != null)
			this.size = dataProvider.size();
	}


	public ShowAllLink(final String id, final int size, final Class<? extends Page> pageClazz)
	{
		this(id, null);
		this.size = size;
		this.pageClazz = pageClazz;
	}

	@Override
	public boolean isVisible()
	{
		if (size > 9)
		{
			return true;
		}
		return false;
	}


	@Override
	public void onClick()
	{
		if (pageClazz == null)
		{
			setResponsePage(new AllUsersPage(dataProvider));
		}
		else
		{
			setResponsePage(pageClazz);
		}
	}
}
