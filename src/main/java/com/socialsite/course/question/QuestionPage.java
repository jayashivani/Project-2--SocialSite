
package com.socialsite.course.question;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePage;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.course.CoursePage;
import com.socialsite.course.answer.AddAnswerPanel;
import com.socialsite.course.answer.AnswersPanel;
import com.socialsite.dao.QuestionDao;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Question;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;
import com.socialsite.util.DateUtils;


@AuthorizeInstantiation( { "USER", "STAFF" })
public class QuestionPage extends BasePage
{


	@SpringBean(name = "questionDao")
	QuestionDao questionDao;

	public QuestionPage(final IModel<Question> model)
	{
		final Question question = model.getObject();
		final User user = question.getUser();
		add(new Label("heading", question.getHeading()));
		add(new Label("question", question.getText()).setEscapeModelStrings(false));

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

		MarkupContainer answerPanel;
		add(answerPanel = new AnswersPanel("answers", model));
		add(new AddAnswerPanel("addanswer", model, answerPanel));

		add(new Link<Question>("delete", model)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				Question question = getModelObject();
				Model<Course> model = new Model<Course>(question.getCourse());
				questionDao.delete(getModelObject());
				setResponsePage(new CoursePage(model));
			}

			@Override
			public boolean isVisible()
			{
				return (hasRole(SocialSiteRoles.STAFF) || getModelObject().getUser().getId() == getSessionUserId());
			}
		});

	}
}
