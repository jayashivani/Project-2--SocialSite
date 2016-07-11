
package com.socialsite.course;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;

import com.socialsite.BasePanel;
import com.socialsite.dataprovider.StaffCourseDataProvider;
import com.socialsite.dataprovider.UniversityCourseDataProvider;
import com.socialsite.dataprovider.UserCourseDataProvider;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Admin;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.Student;
import com.socialsite.persistence.User;

/**
 * @author Ananth
 */
public class AllCoursesPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AllCoursesPanel(String id)
	{
		super(id);

		User user = getUser();
		IDataProvider<Course> dataProvider = null;
		if (user instanceof Student)
		{
			dataProvider = new UserCourseDataProvider(user.getId());
		}
		else if (user instanceof Staff)
		{

			dataProvider = new StaffCourseDataProvider(user.getId());
		}
		else if (user instanceof Admin)
		{
			dataProvider = new UniversityCourseDataProvider(((Admin)user).getUniversity().getId());
		}

		final DataView<Course> courseList = new DataView<Course>("courses", dataProvider, 12)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Course> item)
			{
				Course course = item.getModelObject();
				CourseLink courseLink;
				item.add(courseLink = new CourseLink("courselink", new Model<Course>(course)));
				courseLink.add(new Label("coursename", course.getName()));

				CourseLink courseImageLink;
				item.add(courseImageLink = new CourseLink("courseimagelink", new Model<Course>(
						course)));
				courseImageLink.add(new ImagePanel("image", course.getId(), ImageType.COURSE,
						course.getLastModified(), false, false));

				item.add(new Label("students", course.getStudents().size() + ""));
				item.add(new Label("questions", course.getQuestions().size() + ""));

			}

		};

		add(new PagingNavigator("paging", courseList));
		add(courseList);
	}
}
