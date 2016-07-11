
package com.socialsite.home;

import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.BasePage;
import com.socialsite.SocialSiteSession;
import com.socialsite.course.CoursesPanel;
import com.socialsite.dataprovider.FriendsDataProvider;
import com.socialsite.dataprovider.UserCourseDataProvider;
import com.socialsite.friend.FriendsPage;
import com.socialsite.message.MessagePanel;
import com.socialsite.message.MessageSenderPanel;
import com.socialsite.user.UserInfoPanel;
import com.socialsite.user.UsersPanel;


public class HomePage extends BasePage
{

	/**
	 * constructor
	 * 
	 * Home page of the user
	 * 
	 */
	public HomePage()
	{

		long userId = SocialSiteSession.get().getUserId();
		// update the message panel after sending the message panel
		final Panel msgPanel = new MessagePanel("message");
		msgPanel.setOutputMarkupId(true);
		add(new UserInfoPanel("userinfo"));
		add(msgPanel);
		add(new UsersPanel("friends", new FriendsDataProvider(userId), FriendsPage.class));
		add(new CoursesPanel("courses", new UserCourseDataProvider(SocialSiteSession.get()
				.getUserId())));
		add(new MessageSenderPanel("sender", msgPanel));
	}

	@Override
	public void renderHead(final IHeaderResponse response)
	{
		super.renderHead(response);
		response.renderJavascriptReference("js/socialsite/home.js");
	}


}
