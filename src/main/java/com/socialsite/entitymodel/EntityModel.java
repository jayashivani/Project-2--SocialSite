
package com.socialsite.entitymodel;

import com.socialsite.dao.AbstractDao;
import com.socialsite.persistence.AbstractDomain;

public class EntityModel<T extends AbstractDomain> extends AbstractEntityModel<T>
{

	private static final long serialVersionUID = 1L;
	/** DAO to load the domanin object */
	private final AbstractDao<T> dao;

	/**
	 * creates a detachable Domain object
	 * 
	 * @param domain
	 *            detachable Domain object
	 * @param dao
	 *            Dao to acces the Domain object
	 */
	public EntityModel(final T domain, final AbstractDao<T> dao)
	{
		super(domain);
		this.dao = dao;
	}

	/**
	 * returns the Domain if available or fetch it from the database
	 * 
	 * @return Domain
	 */
	@Override
	protected T load()
	{
		if (domain == null)
		{
			domain = dao.load(id);
		}
		return domain;
	}
}
