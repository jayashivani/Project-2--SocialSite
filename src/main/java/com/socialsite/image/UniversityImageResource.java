
package com.socialsite.image;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.AbstractImageDao;
import com.socialsite.dao.UniversityDao;
import com.socialsite.persistence.University;


public class UniversityImageResource extends AbstractImageResource<University>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** used to access the university images **/
	@SpringBean(name = "universityDao")
	private UniversityDao universityDao;


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.image.AbstractImageResource#getImageDao()
	 */
	@Override
	public AbstractImageDao<University> getImageDao()
	{
		if (universityDao == null)
		{
			// inject the spring dao
			InjectorHolder.getInjector().inject(this);
		}
		return universityDao;
	}

}
