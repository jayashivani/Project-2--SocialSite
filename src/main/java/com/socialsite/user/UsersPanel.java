package com.socialsite.user;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import com.socialsite.BasePanel;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.User;
import com.socialsite.util.ShowAllLink;


public class UsersPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsersPanel(String id, IDataProvider<User> dataProvider, Class<? extends Page> showAllPage)
	{
		super(id);

		final DataView<User> userList = new DataView<User>("users", dataProvider, 9)
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
				userImageLink.add(new ImagePanel("userthumb", user.getId(), ImageType.USER, user
						.getLastModified(), true));
				Link<User> name;
				item.add(name = new UserLink<User>("home", item.getModel()));
				name.add(new Label("username", item.getModelObject().getUserName()));
			}

		};
		add(new ShowAllLink("showall", userList.getDataProvider()));
		add(userList);
	}

}
