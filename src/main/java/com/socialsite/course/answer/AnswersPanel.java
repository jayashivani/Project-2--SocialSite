
package com.socialsite.course.answer;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.dataprovider.AnswerDataProvider;
import com.socialsite.persistence.Answer;
import com.socialsite.persistence.Question;


public class AnswersPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MarkupContainer answersContainer;

	public AnswersPanel(final String id, final IModel<Question> model)
	{
		super(id, model);
		// allow other panels to update this panel using ajax
		setOutputMarkupId(true);
		final Question question = model.getObject();
		add(answersContainer = new WebMarkupContainer("answerscontainer"));
		answersContainer.setOutputMarkupId(true);

		// TODO add the answers and other things
		final DataView<Answer> answerView = new DataView<Answer>("answers", new AnswerDataProvider(
				question.getId()))
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Answer> item)
			{
				item.add(new AnswerPanel("answer", item.getModel(), answersContainer));
			}
		};
		answersContainer.add(answerView);
	}

}
