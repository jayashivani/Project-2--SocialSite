
package com.socialsite.image;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.AbstractImageDao;
import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;
public class UserImageResource extends AbstractImageResource<Profile>
{

	@SpringBean(name = "profileDao")
	private ProfileDao profileDao;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.image.AbstractImageResource#getImageDao()
	 */
	@Override
	public AbstractImageDao<Profile> getImageDao()
	{
		if (profileDao == null)
		{
			// inject the spring dao
			InjectorHolder.getInjector().inject(this);
		}

		return profileDao;
	}

}
