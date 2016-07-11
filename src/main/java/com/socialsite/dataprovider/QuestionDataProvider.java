
package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.QuestionDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Question;


public class QuestionDataProvider extends SortableDataProvider<Question>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "questionDao")
	private QuestionDao questionDao;

	private final long courseId;

	public QuestionDataProvider(final long courseId)
	{
		InjectorHolder.getInjector().inject(this);
		// reloads the university from DB.Avoids LIE
		this.courseId = courseId;
	}

	public Iterator<? extends Question> iterator(final int first, final int count)
	{
		return questionDao.getQuestions(courseId, first, count).iterator();
	}

	public IModel<Question> model(final Question model)
	{
		return new EntityModel<Question>(model, questionDao);
	}

	public int size()
	{
		return questionDao.getQuestionsCount(courseId);
	}

}
