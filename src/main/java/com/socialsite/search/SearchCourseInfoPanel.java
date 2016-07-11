
package com.socialsite.search;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.course.CourseLink;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Course;

public class SearchCourseInfoPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchCourseInfoPanel(String id, IModel<Course> model)
	{
		super(id, model);
		Course course = model.getObject();
		add(new ImagePanel("image", course.getId(), ImageType.COURSE, course.getLastModified(),
				false, false));
		CourseLink courseLink;
		add(courseLink = new CourseLink("courselink", new Model<Course>(course)));
		courseLink.add(new Label("coursename", course.getName()));

		add(new Label("students", course.getStudents().size() + ""));
		add(new Label("questions", course.getQuestions().size() + ""));


	}

}
