
package com.socialsite.dao.hibernate;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.socialsite.dao.AbstractDao;

/**
 * Abstract DAO implementation for all the Domain class
 * 
 * 
 */
public abstract class AbstractDaoImpl<T> implements AbstractDao<T>
{

	/** class of the domain */
	protected final Class<T> domainClass;
	/** Hibernate sessionFactory to access the database */
	private SessionFactory sessionFactory;

	/**
	 * constructor
	 * 
	 * @param domainClass
	 *            domainClass
	 */
	public AbstractDaoImpl(final Class<T> domainClass)
	{
		this.domainClass = domainClass;
	}

	/**
	 * helper method to find the count of item that match the filter
	 * 
	 * @param filter
	 *            filter text
	 * @param clazz
	 *            class of the item
	 * @param field
	 *            field that will be matched with the criteria
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected int count(String filter, final Class clazz, final String field)
	{
		// Avoids NPE
		filter = filter == null ? "" : filter;

		final Criteria criteria = getSession().createCriteria(clazz);
		criteria.add(Restrictions.ilike(field, filter, MatchMode.ANYWHERE));
		criteria.setProjection(Projections.rowCount());
		return (Integer)criteria.uniqueResult();
	}

	/**
	 * find the number of unique rows in the given domainclass (table)
	 * 
	 * @return no of unique rows in the given domainclass (table)
	 */
	public int countAll()
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.setProjection(Projections.rowCount());
		return (Integer)criteria.uniqueResult();
	}

	/**
	 * removes the given domainclass object from the Database
	 * 
	 * @param object
	 *            object to be deleted
	 */
	public void delete(final T object)
	{
		getSession().delete(object);
	}

	/**
	 * a helper method to find the list of item matching the filter
	 * 
	 * @param filter
	 *            filter text
	 * @param first
	 *            first index of the resul
	 * @param count
	 *            count
	 * @param sortParam
	 *            sorting is done based on this property
	 * @param clazz
	 *            class of the item
	 * @param field
	 *            field that will be matched with the criteria
	 * @return list of item matching the filter text
	 */
	@SuppressWarnings("unchecked")
	protected List find(String filter, final int first, final int count, final SortParam sortParam,
			final Class clazz, final String field)
	{
		// avoids the NPE
		filter = filter == null ? "" : filter;

		// order
		final Order order = sortParam.isAscending() ? Order.asc(sortParam.getProperty()) : Order
				.desc(sortParam.getProperty());
		final Criteria criteria = getSession().createCriteria(clazz);
		criteria.setFirstResult(first);
		criteria.setMaxResults(count);
		criteria.add(Restrictions.ilike(field, filter, MatchMode.ANYWHERE));
		criteria.addOrder(order);
		return criteria.list();
	}

	/**
	 * find all the rows in the table and return it as a list
	 * 
	 * @return List contains all the rows in the given domainclass (table)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll()
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		return criteria.list();
	}


	/**
	 * get hibernate Session from SessionFactory
	 * 
	 * @return Hibernate Session
	 */
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	/**
	 * getter
	 * 
	 * @return hibernate SessionFactory
	 */
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}


	/**
	 * load the domainclass of the given id
	 * 
	 * @param id
	 *            id of the object
	 * @return entity object or null if the id doesn't match
	 */
	@SuppressWarnings("unchecked")
	public T load(final long id)
	{
		return (T)getSession().get(domainClass, id);
	}

	/**
	 * merges the Detached object with the Session
	 * 
	 * @param object
	 *            object to be merged
	 */
	@SuppressWarnings("unchecked")
	public T merge(final T object)
	{
		return (T)getSession().merge(object);
	}

	/**
	 * saves or update the object state in the database
	 * 
	 * @param object
	 *            object to be saved
	 */
	public void save(final T object)
	{
		getSession().saveOrUpdate(object);
	}

	/**
	 * setter
	 * 
	 * @param sessionFactory
	 *            Hibernate sessionFactory
	 */
	public void setSessionFactory(final SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
}
