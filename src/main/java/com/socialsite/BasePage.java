

package com.socialsite;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UserDao;
import com.socialsite.persistence.User;


@AuthorizeInstantiation( { "USER", "FRIEND", "OWNER" })
public class BasePage extends WebPage implements IHeaderContributor
{
	private static final long serialVersionUID = 1L;

	protected HeaderPanel headerPanel;

	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	/**
	 * Constructor
	 */
	public BasePage()
	{
		this(null);
	}

	/**
	 * Constructor.
	 * 
	 * @param model
	 */
	public BasePage(final IModel<?> model)
	{
		super(model);
		// header panel
		add(headerPanel = new HeaderPanel("header"));

	}

	public void renderHead(final IHeaderResponse response)
	{
		// NOTE add all the css references here.Don't add css link in the other
		// pages or panel.This will help in combing all the css files into
		// single file during deployment


		response.renderCSSReference("css/libraries.css");
		response.renderCSSReference("css/forms.css");
		response.renderCSSReference("css/template/template.css");
		response.renderCSSReference("css/grid/grids.css");
		response.renderCSSReference("css/content.css");
		response.renderCSSReference("css/module/mod.css");
		response.renderCSSReference("css/module/mod_skins.css");
		response.renderCSSReference("css/talk/talk.css");
		response.renderCSSReference("css/talk/talk_skins.css");
		response.renderCSSReference("css/global.css");
		response.renderCSSReference("css/home.css");
		// response.renderCSSReference("css/login.css");
		response.renderCSSReference("css/profile.css");
		// response.renderCSSReference("css/typography.css");
		response.renderCSSReference("css/round.css");
		response.renderCSSReference("css/wmd.css");
		response.renderCSSReference("css/menu.css");
		response.renderCSSReference("css/skin.css");
		response.renderCSSReference("css/button.css");


		// renders the jquery and socialsite in all pages
		response.renderJavascriptReference("js/jquery/jquery.js");
		response.renderJavascriptReference("js/socialsite/socialsite.js");


	}

	/**
	 * set the user id in the session and also sets the roles in the session
	 * 
	 * @param userId
	 *            user id
	 */
	public void setUserId(final long userId)
	{
		final SocialSiteSession session = SocialSiteSession.get();
		// set the user id
		session.setUserId(userId);
		// set the roles
		session.getSessionUser().setRoles(
				userDao.getUsersRelation(userId, session.getSessionUser().getId()));
	}

	/**
	 * helper to check the role
	 * 
	 * @param role
	 *            role
	 */
	public boolean hasRole(final String role)
	{
		return SocialSiteSession.get().getSessionUser().hasRole(role);
	}

	/**
	 * gets the id of the session user
	 * 
	 * @return id of the session user
	 */
	public long getSessionUserId()
	{
		return SocialSiteSession.get().getSessionUser().getId();
	}

	/**
	 * gets the user id(the visitor id)
	 * 
	 * @return visitor id
	 */
	public long getUserId()
	{
		return SocialSiteSession.get().getUserId();
	}
}
