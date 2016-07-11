
package com.socialsite.staff;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import com.socialsite.BasePanel;
import com.socialsite.dataprovider.StaffDataProvider;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;
import com.socialsite.util.ShowAllLink;


public class StaffsPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StaffsPanel(final String id, final University university)
	{
		super(id);
		// friends data provider
		final StaffDataProvider staffDataProvider = new StaffDataProvider(university);

		final DataView<User> staffList = new DataView<User>("staffs", staffDataProvider, 9)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<User> item)
			{
				final User staff = item.getModelObject();
				UserLink<User> userImageLink;
				item.add(userImageLink = new UserLink<User>("imagelink", item.getModel()));
				userImageLink.add(new ImagePanel("userthumb", staff.getId(), ImageType.USER, staff
						.getLastModified(), true));
				Link<User> name;
				item.add(name = new UserLink<User>("home", item.getModel()));
				name.add(new Label("username", item.getModelObject().getUserName()));
			}

		};
		add(new ShowAllLink("showall", staffList.getDataProvider()));
		add(staffList);

	}
}
