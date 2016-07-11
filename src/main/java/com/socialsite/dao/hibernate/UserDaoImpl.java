
package com.socialsite.dao.hibernate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.User;

public class UserDaoImpl<T extends User> extends AbstractDaoImpl<T> implements UserDao<T>
{

	/**
	 * constructor
	 * 
	 * @param domainClass
	 *            domainclass
	 */
	public UserDaoImpl(final Class<T> domainClass)
	{
		super(domainClass);
	}

	/*
	 *
	 * 
	 * @see com.socialsite.dao.UserDao#checkUserStatus(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public T checkUserStatus(final String userName, final String password)
	{
		final Criteria criteria = getSession().createCriteria(domainClass);
		criteria.add(Restrictions.eq("userName", userName));
		final T user = (T)criteria.uniqueResult();

		if (user != null && user.matchPassword(password))
		{
			return user;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.UserDao#countAll(java.lang.String)
	 */
	public int countAll(final String filter)
	{
		return count(filter, User.class, "userName");
	}

	/*
	 * 
	 * 
	 * @see com.socialsite.dao.UserDao#findAll(java.lang.String, int, int,
	 * org.apache.wicket.extensions.markup.html.repeater.util.SortParam)
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll(final String filter, final int first, final int count,
			final SortParam sortParam)
	{
		return find(filter, first, count, sortParam, User.class, "userName");
	}

	/*
	 * 
	 * 
	 * @see
	 * com.socialsite.dao.UserDao#getFriends(com.socialsite.persistence.User,
	 * int, int)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getFriends(final long userId, final int first, final int count)
	{
		final Query query = getSession().createQuery(
				"select u.friends from User u where u.id = :userId").setParameter("userId", userId)
				.setFetchSize(count).setFirstResult(first).setMaxResults(count);
		return query.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.socialsite.dao.UserDao#getFriends(com.socialsite.persistence.User)
	 */
	@SuppressWarnings("unchecked")
	public List<T> getFriends(final T user)
	{
		return (List<T>)new ArrayList<User>(user.getFriends());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.socialsite.dao.UserDao#getFriendsCount(com.socialsite.persistence
	 * .User)
	 */
	public int getFriendsCount(final long userId)
	{

		final BigInteger count = (BigInteger)getSession().createSQLQuery(
				" select count(*) from friend_reference where user_id=:id  ").setParameter("id",
				userId).uniqueResult();

		return count.intValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.socialsite.dao.UserDao#getUsersRelation(long, long)
	 */
	public Roles getUsersRelation(final long id1, final long id2)
	{
		// owner
		if (id1 == id2)
		{
			return SocialSiteRoles.ownerRole;
		}

		final BigInteger count = (BigInteger)getSession().createSQLQuery(
				"select count(*) from friend_reference where"
						+ " (friend_id = :id1 and user_id = :id2)").setParameter("id1", id1)
				.setParameter("id2", id2).uniqueResult();

		// friend
		if (count.intValue() == 1)
		{
			return SocialSiteRoles.friendRole;
		}
		else
		{ // unknown
			return SocialSiteRoles.userRole;
		}
	}

}
