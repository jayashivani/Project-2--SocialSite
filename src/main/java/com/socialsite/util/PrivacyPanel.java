
package com.socialsite.util;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.profile.Access;


public class PrivacyPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final PrivacyModel privacyModel;

	public PrivacyPanel(final String id, final IModel<PrivacyModel> model, final String label)
	{
		super(id, model);
		privacyModel = model.getObject();
		add(new Label("field", label));
		add(new Label("value", privacyModel.getValue()));

	}

	@Override
	public boolean isVisible()
	{
		// Don't show empty values
		if (privacyModel == null || privacyModel.getValue() == null)
		{
			return false;
		}
		final Access access = privacyModel.getPrivacy();
		if (access == Access.EVERYONE || hasRole(SocialSiteRoles.OWNER)
				|| access == Access.FRIENDS_ONLY && hasRole(SocialSiteRoles.FRIEND))
		{
			return true;
		}
		return false;
	}
}
