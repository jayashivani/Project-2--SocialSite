
package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.CommentDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Comment;


public class CommentDataProvider extends SortableDataProvider<Comment>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final long answerId;

	@SpringBean(name = "commentDao")
	private CommentDao commentDao;

	public CommentDataProvider(final long answerId)
	{
		InjectorHolder.getInjector().inject(this);
		this.answerId = answerId;
	}

	public Iterator<Comment> iterator(final int first, final int count)
	{
		return commentDao.getComments(answerId, first, count).iterator();
	}

	public IModel<Comment> model(final Comment model)
	{
		return new EntityModel<Comment>(model, commentDao);
	}

	public int size()
	{
		return commentDao.getCommentsCount(answerId);
	}

}
