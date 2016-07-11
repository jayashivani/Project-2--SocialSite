
package com.socialsite.message;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.course.CourseLink;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.CourseJoinedMsg;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;
import com.socialsite.util.DateUtils;


public class CourseJoinedMsgPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CourseJoinedMsgPanel(final String id, final IModel<CourseJoinedMsg> model,
			final MarkupContainer dependent)
	{
		super(id);
		final CourseJoinedMsg courseJoinedMsg = model.getObject();
		final User sender = courseJoinedMsg.getSender();
		final Course course = courseJoinedMsg.getCourse();

		// user image
		UserLink<User> userImageLink;
		final Model<User> senderModel = new Model<User>(sender);
		add(userImageLink = new UserLink<User>("imagelink", senderModel));
		userImageLink.add(new ImagePanel("userthumb", sender.getId(), ImageType.USER, sender
				.getLastModified(), true));
		Link<User> name;
		add(name = new UserLink<User>("home", senderModel));
		name.add(new Label("username", sender.getUserName()));

		CourseLink courseLink;
		add(courseLink = new CourseLink("course", new Model<Course>(course)));
		courseLink.add(new Label("courseName", course.getName()));


		add(new Label("date", DateUtils.relativeTime(courseJoinedMsg.getTime())));

		// delete link
		add(new DeleteMsgLink<CourseJoinedMsg>("delete", model, dependent, this, sender.getId()));
		setOutputMarkupId(true);
	}

}
