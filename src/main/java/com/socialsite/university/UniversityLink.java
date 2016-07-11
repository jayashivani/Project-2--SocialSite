
package com.socialsite.university;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.persistence.University;


public class UniversityLink extends Link<University>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UniversityLink(final String id, final IModel<University> model)
	{
		super(id, model);
	}

	@Override
	public void onClick()
	{

		SessionUser user = SocialSiteSession.get().getSessionUser();
		if (user.getId() == getModelObject().getAdmin().getId())
			user.setRoles(SocialSiteRoles.ownerRole);
		else
			user.setRoles(SocialSiteRoles.userRole);

		setResponsePage(new UniversityPage(getModelObject()));
	}
}
