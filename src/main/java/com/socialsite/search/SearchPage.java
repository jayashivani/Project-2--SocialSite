
package com.socialsite.search;

import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import com.socialsite.BasePage;
import com.socialsite.dataprovider.SearchDataProvider;
import com.socialsite.entitymodel.StringWrapper;
import com.socialsite.user.UserInfoPanel;


public class SearchPage extends BasePage
{

	/** search filter **/
	private final StringWrapper filter;

	/**
	 * constructor
	 * 
	 * @param searchText
	 *            search text
	 */
	@SuppressWarnings("unchecked")
	public SearchPage(final StringWrapper filter, final SearchOption searchOption)
	{
		// intialize the filter
		this.filter = filter;

		// add the user info panel
		add(new UserInfoPanel("userinfo"));

		final SearchDataProvider searchDataProvider = new SearchDataProvider(this.filter,
				searchOption);

		final DataView searchList = new DataView("searchlist", searchDataProvider, 9)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item item)
			{
				switch (searchOption)
				{
					case USER :
						item.add(new SearchUserInfoPanel("details", item.getModel()));
						break;
					case UNIVERSITY :
						item.add(new SearchUniversityInfo("details", item.getModel()));
						break;
					case COURSE :
						item.add(new SearchCourseInfoPanel("details", item.getModel()));
				}
			}
		};

		add(new PagingNavigator("searchpagination", searchList));
		add(searchList);
	}
}
