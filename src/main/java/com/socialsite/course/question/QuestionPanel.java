
package com.socialsite.course.question;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SessionUser;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.QuestionDao;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Question;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;
import com.socialsite.util.DateUtils;

/**
 * @author Ananth
 */
public class QuestionPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@SpringBean(name = "questionDao")
	QuestionDao questionDao;

	public QuestionPanel(final String id, final IModel<Question> model,
			final MarkupContainer dependent)
	{
		super(id, model);
		final Question question = model.getObject();

		final User user = question.getUser();

		// user image
		UserLink<User> userImageLink;
		final Model<User> senderModel = new Model<User>(user);
		add(userImageLink = new UserLink<User>("imagelink", senderModel));
		userImageLink.add(new ImagePanel("userthumb", user.getId(), ImageType.USER, user
				.getLastModified(), true));
		Link<User> name;
		add(name = new UserLink<User>("home", senderModel));
		name.add(new Label("username", user.getUserName()));


		add(new Label("date", DateUtils.relativeTime(question.getTime())));

		final Link<Question> questionLink = new Link<Question>("questionlink", model)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				SessionUser user = SocialSiteSession.get().getSessionUser();
				if (getModelObject().getCourse().getStaff().getId() == user.getId())
				{
					user.setRoles(SocialSiteRoles.staffRole);
				}
				else
				{
					user.setRoles(SocialSiteRoles.userRole);
				}

				setResponsePage(new QuestionPage(model));
			}

		};
		add(questionLink);
		questionLink.add(new Label("heading", question.getHeading()));

		// Question

		// TODO show only first two lines of the Question
		add(new Label("question", question.getText()).setEscapeModelStrings(false));


		add(new AjaxLink<Question>("delete", model)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				questionDao.delete(getModelObject());
				target.addComponent(dependent);
			}

			@Override
			public boolean isVisible()
			{
				return (hasRole(SocialSiteRoles.STAFF) || getSessionUserId() == getModelObject()
						.getUser().getId());
			}
		});
	}
}
