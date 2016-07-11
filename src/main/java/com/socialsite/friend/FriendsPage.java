
package com.socialsite.friend;

import com.socialsite.BasePage;

public class FriendsPage extends BasePage
{
	public FriendsPage()
	{
		add(new AllFriendsPanel("friends"));
	}
}
