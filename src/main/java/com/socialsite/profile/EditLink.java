package com.socialsite.profile;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.authentication.SocialSiteRoles;


public class EditLink extends AjaxLink<Void>
{

	private static final long serialVersionUID = 1L;
	Panel other;
	Panel current;
	Roles roles;
	String state = "edit";

	public EditLink(final String id, final Panel current, final Panel other, final Roles roles)
	{
		super(id);
		this.current = current;
		this.other = other;
		this.roles = roles;
	}

	@Override
	public boolean isVisible()
	{
		return roles.hasRole(SocialSiteRoles.OWNER) && state.equals("edit");
	}

	@Override
	public void onClick(final AjaxRequestTarget target)
	{
		current.replaceWith(other);
		final Panel temp = current;
		current = other;
		other = temp;
		state = state.equals("edit") ? "" : "edit";
		target.addComponent(this);
		target.addComponent(current);
	}

	@Override
	protected void onComponentTagBody(final MarkupStream markupStream, final ComponentTag openTag)
	{
		replaceComponentTagBody(markupStream, openTag, "<span>" + state + "</span>");
	}
}
