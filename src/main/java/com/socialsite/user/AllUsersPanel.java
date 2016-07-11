
package com.socialsite.user;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import com.socialsite.BasePanel;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.User;

public class AllUsersPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AllUsersPanel(String id, IDataProvider<User> dataProvider)
	{
		super(id);
		final DataView<User> userList = new DataView<User>("friends", dataProvider, 12)
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

		add(new PagingNavigator("paging", userList));
		add(userList);
	}

}
