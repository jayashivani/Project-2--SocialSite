
package com.socialsite.course;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.CourseDao;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageService;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;

/**
 * @author Ananth
 */
public class CourseInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "courseDao")
	private CourseDao courseDao;

	public CourseInfoPanel(final String id, final IModel<Course> model)
	{
		super(id, model);
		final Course course = model.getObject();
		add(new ImagePanel("image", course.getId(), ImageType.COURSE, course.getLastModified())
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void saveImage(final byte[] imageData)
			{
				final ImageService imageService = new ImageService();
				course.changeImage(imageService.resize(imageData, ImageService.IMAGE_SIZE));
				course.changeThumb(imageService.resize(imageData, ImageService.THUMB_SIZE));
				courseDao.save(course);
			}

		});

		add(new Label("coursename", course.getName()));
		add(new Label("description", course.getDescription()));

		// join this course
		add(new JoinCoursePanel("join", model));

		UserLink<User> staffLink;
		add(staffLink = new UserLink<User>("stafflink", new Model<User>(course.getStaff())));
		staffLink.add(new Label("name", course.getStaff().getUserName()));

		add(new AddNotePanel("addnote", model));
		add(new Label("students", course.getStudents().size() + ""));
		add(new Label("questions", course.getQuestions().size() + ""));
	}

}
