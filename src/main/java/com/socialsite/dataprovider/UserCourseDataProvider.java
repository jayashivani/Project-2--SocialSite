
package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.CourseDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Course;


public class UserCourseDataProvider extends SortableDataProvider<Course>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@SpringBean(name = "courseDao")
	private CourseDao courseDao;
	private final long id;

	public UserCourseDataProvider(final long id)
	{
		InjectorHolder.getInjector().inject(this);
		this.id = id;
	}


	public Iterator<? extends Course> iterator(final int first, final int count)
	{
		return courseDao.getUserCourses(id, first, count).iterator();
	}

	public IModel<Course> model(final Course course)
	{
		return new EntityModel<Course>(course, courseDao);
	}

	public int size()
	{
		return courseDao.getUserCoursesCount(id);
	}

}
