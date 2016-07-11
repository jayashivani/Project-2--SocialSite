package com.socialsite;

import java.util.Arrays;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.socialsite.authentication.LogoutPage;
import com.socialsite.course.UserCoursesPage;
import com.socialsite.entitymodel.StringWrapper;
import com.socialsite.friend.FriendsPage;
import com.socialsite.home.HomePage;
import com.socialsite.profile.ProfilePage;
import com.socialsite.search.SearchOption;
import com.socialsite.search.SearchPage;

public class HeaderPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Model for search text box */
	public StringWrapper filter;

	/*
	 * construct
	 */
	public HeaderPanel(final String id)
	{
		super(id);

		add(new Link<Void>("home")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setUserId(SocialSiteSession.get().getSessionUser().getId());
				setResponsePage(HomePage.class);
			}

		});

		add(new Link<Void>("profile")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				// TODO always show the session user profile
				setUserId(getUserId());
				setResponsePage(ProfilePage.class);
			}

		});

		add(new Link<Void>("friends")
		{

			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setUserId(SocialSiteSession.get().getSessionUser().getId());
				setResponsePage(FriendsPage.class);
			}

		});

		add(new Link<Void>("courses")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				setUserId(SocialSiteSession.get().getSessionUser().getId());
				setResponsePage(new UserCoursesPage());
			}

		});

		add(new BookmarkablePageLink<Object>("logout", LogoutPage.class));

		filter = new StringWrapper();
		SubmitLink search;
		final Form<Object> searchForm = new Form<Object>("searchform");
		add(searchForm);

		// search box
		searchForm.add(new TextField<String>("searchtextbox", new PropertyModel<String>(this,
				"filter.model")));

		// search options
		final DropDownChoice<SearchOption> searchOptions = new DropDownChoice<SearchOption>(
				"options", Arrays.asList(SearchOption.values()));
		searchOptions.setDefaultModel(new Model<SearchOption>(SearchOption.USER));
		searchForm.add(searchOptions);

		searchForm.add(search = new SubmitLink("search")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit()
			{
				setResponsePage(new SearchPage(filter, searchOptions.getModelObject()));
			}
		});
		searchForm.setDefaultButton(search);

	}
}
