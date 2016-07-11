
package com.socialsite.friend;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.dataprovider.FriendsDataProvider;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;


public class AllFriendsPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AllFriendsPanel(final String id)
	{
		super(id);
		// friends data provider
		final FriendsDataProvider friendsDataProvider = new FriendsDataProvider(SocialSiteSession
				.get().getUserId());

		final DataView<User> friendList = new DataView<User>("friends", friendsDataProvider, 12)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<User> item)
			{
				final User user = item.getModelObject();
				UserLink<User> userImageLink;
				item.add(userImageLink = new UserLink<User>("imagelink", item.getModel()));
				userImageLink.add(new ImagePanel("user", user.getId(), ImageType.USER, user
						.getLastModified(), false, false));
				Link<User> name;
				item.add(name = new UserLink<User>("home", item.getModel()));
				name.add(new Label("username", item.getModelObject().getUserName()));

				item.add(new Label("city", user.getProfile().getCurrentCity().getValue()));
				item.add(new Label("sex", user.getProfile().getSex()));

			}

		};

		add(new PagingNavigator("paging", friendList));
		add(friendList);
	}

}
