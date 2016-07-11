

package com.socialsite.user;

import org.apache.wicket.markup.repeater.data.IDataProvider;

import com.socialsite.BasePage;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class AllUsersPage extends BasePage
{

	public AllUsersPage(IDataProvider<User> dataProvider)
	{
		add(new AllUsersPanel("users", dataProvider));
	}
}
