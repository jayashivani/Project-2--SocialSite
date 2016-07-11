
package com.socialsite.profile;

import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Profile;
import com.socialsite.util.NonEmptyPanel;
import com.socialsite.util.PrivacyModel;
import com.socialsite.util.PrivacyPanel;

public class ContactInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Profile profile;


	public ContactInfoPanel(final String id)
	{
		super(id);
		profile = getUser().getProfile();
		setOutputMarkupId(true);
		// should we show the email
		add(new NonEmptyPanel("email", "Email", profile.getEmail()));
		add(new PrivacyPanel("mobilePhone", new Model<PrivacyModel>(profile.getMobilePhone()),
				"MobilePhone"));
		add(new PrivacyPanel("landPhone", new Model<PrivacyModel>(profile.getLandPhone()),
				"LandPhone"));
		add(new PrivacyPanel("address", new Model<PrivacyModel>(profile.getAddress()), "Address"));
		add(new NonEmptyPanel("city", "City", profile.getCity()));
		add(new NonEmptyPanel("neighborhood", "Neighborhood", profile.getNeighborhood()));

		add(new NonEmptyPanel("zip", "Zip", profile.getZip() == null ? null : profile.getZip() + ""));
		add(new NonEmptyPanel("website", "WebSite", profile.getWebsite()));

	}
}
