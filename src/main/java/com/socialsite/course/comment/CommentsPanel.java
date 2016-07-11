
package com.socialsite.course.comment;

import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;

import com.socialsite.BasePanel;
import com.socialsite.dataprovider.CommentDataProvider;
import com.socialsite.persistence.Answer;
import com.socialsite.persistence.Comment;


public class CommentsPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentsPanel(final String id, final IModel<Answer> model)
	{
		super(id, model);
		// allow other panels to update this panel using ajax
		setOutputMarkupId(true);
		final Answer answer = model.getObject();
		final DataView<Comment> commentView = new DataView<Comment>("comments",
				new CommentDataProvider(answer.getId()))
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Comment> item)
			{
				item.add(new CommentPanel("comment", item.getModel()));
			}
		};
		add(commentView);
	}

}
