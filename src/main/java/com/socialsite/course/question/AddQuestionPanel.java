
package com.socialsite.course.question;

import java.util.Date;
import java.util.HashSet;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.MessageDao;
import com.socialsite.dao.QuestionDao;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.Question;
import com.socialsite.persistence.QuestionInfoMsg;
import com.socialsite.persistence.User;
import com.socialsite.util.wmd.RichEditor;

public class AddQuestionPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "questionDao")
	private QuestionDao questionDao;

	@SpringBean(name = "messageDao")
	private MessageDao<Message> messageDao;

	/** feedback panel */
	FeedbackPanel feedback;
	/** model for question heading */
	private String heading;
	/** model for question text */
	private String text;

	public AddQuestionPanel(final String id, final IModel<Course> model,
			final MarkupContainer dependent)
	{
		super(id, model);
		final Form<Course> form = new Form<Course>("form", model);
		add(form);
		form.add(new RequiredTextField<String>("heading",
				new PropertyModel<String>(this, "heading")));
		form.add(new RichEditor("richeditor", new PropertyModel<String>(this, "text")));
		final AjaxSubmitLink addQuestionLink = new AjaxSubmitLink("addquestion", form)
		{

			/** */
			private static final long serialVersionUID = 1L;


			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				super.onError(target, form);
				// show feedback messages
				target.addComponent(feedback);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				// save the question
				final Course course = (Course)form.getModelObject();
				// create another question model
				final Question question = new Question(heading, text);
				question.setCourse(course);
				question.setUser(getSessionUser());
				questionDao.save(question);

				QuestionInfoMsg infoMsg = new QuestionInfoMsg();
				infoMsg.setQuestion(question);
				infoMsg.setTime(new Date());
				infoMsg.setUsers(new HashSet<User>(course.getStudents()));
				messageDao.save(infoMsg);


				// update the related contents
				target.addComponent(dependent);
				target.addComponent(feedback);
				// fire the update event so the editor can intialize
				firePostAjaxUpdateEvent(target);
				// slideup the reply panel
				final String id = AddQuestionPanel.this.getMarkupId();
				target.appendJavascript(" $('#" + id + " .slideText').trigger('click'); ");
			}
		};

		form.add(addQuestionLink);
		form.setDefaultButton(addQuestionLink);
		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
		setOutputMarkupId(true);
	}
}
