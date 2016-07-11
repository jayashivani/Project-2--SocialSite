
package com.socialsite.course;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePage;
import com.socialsite.course.question.AddQuestionPanel;
import com.socialsite.course.question.QuestionsPanel;
import com.socialsite.dao.CourseDao;
import com.socialsite.dataprovider.CourseStudentsDataProvider;
import com.socialsite.friend.FriendsPage;
import com.socialsite.persistence.Course;
import com.socialsite.user.UsersPanel;

@AuthorizeInstantiation( { "USER", "STAFF" })
public class CoursePage extends BasePage
{
	/** spring dao to handle message object */
	@SpringBean(name = "courseDao")
	private CourseDao courseDao;

	public CoursePage(final IModel<Course> model)
	{
		// reload the model
		model.setObject(courseDao.load(model.getObject().getId()));
		add(new CourseInfoPanel("info", model));

		final QuestionsPanel questionsPanel = new QuestionsPanel("questions", model);
		add(questionsPanel);
		add(new AddQuestionPanel("addquestion", model, questionsPanel.getQuestionsContainer()));

		add(new UsersPanel("students", new CourseStudentsDataProvider(model.getObject().getId()),
				FriendsPage.class));
	}
}
