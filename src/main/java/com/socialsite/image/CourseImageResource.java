
package com.socialsite.image;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.AbstractImageDao;
import com.socialsite.dao.CourseDao;
import com.socialsite.persistence.Course;

public class CourseImageResource extends AbstractImageResource<Course>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/** used to access course image */
	@SpringBean(name = "courseDao")
	private CourseDao courseDao;

	public CourseImageResource()
	{

		// setImageDao(courseDao);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.image.AbstractImageResource#getImageDao()
	 */
	@Override
	public AbstractImageDao<Course> getImageDao()
	{
		if (courseDao == null)
		{
			// inject the spring dao
			InjectorHolder.getInjector().inject(this);
		}
		return courseDao;
	}

}
