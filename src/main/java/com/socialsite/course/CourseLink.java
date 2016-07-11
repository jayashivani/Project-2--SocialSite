
package com.socialsite.course;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.persistence.Course;

public class CourseLink extends Link<Course>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseLink(final String id, final IModel<Course> model)
	{
		super(id, model);
	}

	@Override
	public void onClick()
	{
		// TODO maybe we should add student role
		SessionUser user = SocialSiteSession.get().getSessionUser();
		if (getModelObject().getStaff().getId() == user.getId())
		{
			user.setRoles(SocialSiteRoles.staffRole);
		}
		else
		{
			user.setRoles(SocialSiteRoles.userRole);
		}
		setResponsePage(new CoursePage(getModel()));
	}

}
