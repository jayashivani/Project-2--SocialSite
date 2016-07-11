
package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.CourseDao;
import com.socialsite.dao.UserDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.persistence.User;

public class CourseStudentsDataProvider extends SortableDataProvider<User>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private final long courseId;


	@SpringBean(name = "courseDao")
	private CourseDao courseDao;
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;


	public CourseStudentsDataProvider(long courseId)
	{
		this.courseId = courseId;
		// intializes spring DAO
		InjectorHolder.getInjector().inject(this);
	}

	public Iterator<? extends User> iterator(int first, int count)
	{
		return courseDao.getStudents(courseId, first, count).iterator();
	}

	public IModel<User> model(User user)
	{
		return new EntityModel<User>(user, userDao);
	}

	public int size()
	{
		return courseDao.getStudentsCount(courseId);
	}

}
