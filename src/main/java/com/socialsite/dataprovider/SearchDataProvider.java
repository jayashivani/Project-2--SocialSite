
package com.socialsite.dataprovider;

import java.util.Iterator;

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.CourseDao;
import com.socialsite.dao.UniversityDao;
import com.socialsite.dao.UserDao;
import com.socialsite.entitymodel.EntityModel;
import com.socialsite.entitymodel.StringWrapper;
import com.socialsite.persistence.AbstractDomain;
import com.socialsite.persistence.User;
import com.socialsite.search.SearchOption;


@SuppressWarnings("unchecked")
public class SearchDataProvider extends SortableDataProvider
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** filter text */
	private final StringWrapper filter;
	/** DAO to access the group details */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;
	/** Dao to access the university details */
	@SpringBean(name = "universityDao")
	private UniversityDao universityDao;
	@SpringBean(name = "courseDao")
	private CourseDao courseDao;

	/** contains the search options of the user */
	private final SearchOption searchOption;

	public SearchDataProvider(final StringWrapper filter, final SearchOption searchOption)
	{
		this.filter = filter;
		this.searchOption = searchOption;
		// intializes spring DAO
		InjectorHolder.getInjector().inject(this);

		// set the default sorting
		switch (searchOption)
		{
			case USER :
				setSort("userName", true);
				break;
			case UNIVERSITY :
				setSort("name", true);
				break;
			case COURSE :
				setSort("name", true);
				break;
		}
	}

	public Iterator iterator(final int first, final int count)
	{

		switch (searchOption)
		{
			case USER :
				return userDao.findAll(filter.toString(), first, count, getSort()).iterator();
			case UNIVERSITY :
				return universityDao.findAll(filter.toString(), first, count, getSort()).iterator();
			case COURSE :
				return courseDao.findAll(filter.toString(), first, count, getSort()).iterator();
		}
		return null;
	}

	public IModel model(final Object domain)
	{
		final AbstractDomain absDomain = (AbstractDomain)domain;
		EntityModel model = null;
		switch (searchOption)
		{
			case USER :
				model = new EntityModel(absDomain, userDao);
				break;
			case UNIVERSITY :
				model = new EntityModel(absDomain, universityDao);
				break;
			case COURSE :
				model = new EntityModel(absDomain, courseDao);
				break;
		}

		return model;
	}

	public int size()
	{
		switch (searchOption)
		{
			case USER :
				return userDao.countAll(filter.toString());
			case UNIVERSITY :
				return universityDao.countAll(filter.toString());
			case COURSE :
				return courseDao.countAll(filter.toString());
		}
		return 0;
	}

}
