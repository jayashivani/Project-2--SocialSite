
package com.socialsite.profile;

import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Profile;
import com.socialsite.util.NonEmptyPanel;
import com.socialsite.util.PrivacyModel;
import com.socialsite.util.PrivacyPanel;


public class BasicInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Profile profile;

	public BasicInfoPanel(final String id)
	{
		super(id);
		profile = getUser().getProfile();
		setOutputMarkupId(true);
		add(new NonEmptyPanel("firstName", "First Name", profile.getFirstName()));
		add(new NonEmptyPanel("lastName", "lastName", profile.getLastName()));
		add(new NonEmptyPanel("sex", "Sex", profile.getSex()));
		add(new PrivacyPanel("currentCity", new Model<PrivacyModel>(profile.getCurrentCity()),
				"Current City"));

		add(new PrivacyPanel("homeTown", new Model<PrivacyModel>(profile.getHomeTown()), "homeTown"));
		add(new NonEmptyPanel("relationshipStatus", "Relationship Status", profile
				.getRelationshipStatus()));
		add(new NonEmptyPanel("politicalView", "Political View", profile.getPoliticalView()));
		add(new NonEmptyPanel("religiousView", "Religious View", profile.getReligiousView()));


	}
}
