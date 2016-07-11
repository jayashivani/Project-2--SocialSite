
package com.socialsite.profile;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Profile;
import com.socialsite.util.NonEmptyPanel;


public class EducationInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Profile profile;

	public EducationInfoPanel(final String id)
	{
		super(id);
		profile = getUser().getProfile();
		setOutputMarkupId(true);
		add(new NonEmptyPanel("college", "College/University", profile.getCollege()));
	}

}
