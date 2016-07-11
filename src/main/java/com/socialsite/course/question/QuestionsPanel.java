
package com.socialsite.course.question;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.dataprovider.QuestionDataProvider;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Question;

/**
 * @author Ananth
 */
public class QuestionsPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MarkupContainer questionsContainer;

	public QuestionsPanel(final String id, final IModel<Course> model)
	{
		super(id, model);

		final Course course = model.getObject();

		add(questionsContainer = new WebMarkupContainer("questionscontainer"));
		questionsContainer.setOutputMarkupId(true);

		final DataView<Question> questionsView = new DataView<Question>("questions",
				new QuestionDataProvider(course.getId()), 5)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Question> item)
			{
				item.add(new QuestionPanel("question", item.getModel(), questionsContainer));
			}
		};

		questionsContainer.add(questionsView);

	}

	public MarkupContainer getQuestionsContainer()
	{
		return questionsContainer;
	}


}
