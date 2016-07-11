
package com.socialsite.profile;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Profile;
import com.socialsite.util.NonEmptyPanel;


public class PersonalInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Profile profile;

	public PersonalInfoPanel(final String id)
	{
		super(id);
		profile = getUser().getProfile();
		setOutputMarkupId(true);
		add(new NonEmptyPanel("activity", "Activity", profile.getActivities()));
		add(new NonEmptyPanel("interests", "Interests", profile.getInterests()));
		add(new NonEmptyPanel("favouriteMusic", "Favourite Music", profile.getFavouriteMovies()));
		add(new NonEmptyPanel("favouriteTvShows", "Favourite Tv Shows", profile
				.getFavouriteTvShows()));
		add(new NonEmptyPanel("favouriteMovies", "Favourite Movies ", profile.getFavouriteMovies()));
		add(new NonEmptyPanel("favouriteBooks", "Favourite Books", profile.getFavouriteBooks()));
		add(new NonEmptyPanel("favouriteQuotations", "Favourite Quotations", profile
				.getFavouriteQuotations()));
		add(new NonEmptyPanel("aboutMe", "About Me", profile.getAboutMe()));
	}

}
