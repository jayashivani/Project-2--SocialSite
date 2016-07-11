
package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.AnswerDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Answer;


public class AnswerDataProvider extends SortableDataProvider<Answer>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "answerDao")
	private AnswerDao answerDao;

	private final long questionId;

	public AnswerDataProvider(final long questionId)
	{
		InjectorHolder.getInjector().inject(this);
		// reloads the university from DB.Avoids LIE
		this.questionId = questionId;
	}

	public Iterator<? extends Answer> iterator(final int first, final int count)
	{
		return answerDao.getAnswers(questionId, first, count).iterator();
	}

	public IModel<Answer> model(final Answer model)
	{
		return new EntityModel<Answer>(model, answerDao);
	}

	public int size()
	{
		return answerDao.getAnswersCount(questionId);
	}

}
