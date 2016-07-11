
package com.socialsite.course;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Course;
import com.socialsite.util.ShowAllLink;


public class CoursesPanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 * @param modelId
	 * @param dataProvider
	 */
	public CoursesPanel(final String id, final IDataProvider<Course> dataProvider)
	{
		super(id);
		final DataView<Course> courseView = new DataView<Course>("courses", dataProvider, 9)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Course> item)
			{
				final Course course = item.getModelObject();
				CourseLink courseImageLink;
				item.add(courseImageLink = new CourseLink("imagelink", item.getModel()));
				courseImageLink.add(new ImagePanel("coursethumb", course.getId(), ImageType.COURSE,
						course.getLastModified(), true));

				// course link
				CourseLink courseLink;
				item.add(courseLink = new CourseLink("name", item.getModel()));
				courseLink.add(new Label("coursename", course.getName()));
			}
		};
		add(courseView);
		add(new ShowAllLink("showall", courseView.getDataProvider().size(), UserCoursesPage.class));
	}
}