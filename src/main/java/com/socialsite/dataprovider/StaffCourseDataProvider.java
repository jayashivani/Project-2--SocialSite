
package com.socialsite.dataprovider;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.CourseDao;
import com.socialsite.dao.StaffDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.Course;

public class StaffCourseDataProvider extends SortableDataProvider<Course>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SpringBean(name = "courseDao")
	private CourseDao courseDao;
	@SpringBean(name = "staffDao")
	private StaffDao staffDao;

	private final long id;

	public StaffCourseDataProvider(final long id)
	{
		this.id = id;
		InjectorHolder.getInjector().inject(this);
	}


	public Iterator<? extends Course> iterator(int first, int count)
	{
		return new ArrayList<Course>(staffDao.load(id).getCourses()).subList(first, first + count)
				.iterator();
	}

	public IModel<Course> model(Course course)
	{
		return new EntityModel<Course>(course, courseDao);
	}

	public int size()
	{
		return staffDao.load(id).getCourses().size();
	}
}
