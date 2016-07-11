
package com.socialsite.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


public interface AbstractDao<T>
{

	/**
	 * find the number of unique rows in the given domainclass (table)
	 * 
	 * @return no of unique rows in the given domainclass (table)
	 */
	int countAll();

	/**
	 * removes the given domainclass object from the Database
	 * 
	 * @param object
	 *            object to be deleted
	 */
	@Transactional
	void delete(T o);

	/**
	 * find all the rows in the table and return it as a list
	 * 
	 * @return List contains all the rows in the given domainclass (table)
	 */
	List<T> findAll();

	/**
	 * load the domainclass of the given id
	 * 
	 * @param id
	 *            id of the object
	 * @return entity object or null if the id doesn't match
	 */
	T load(long id);

	/**
	 * merges the Detached object with the Session
	 * 
	 * @param object
	 *            object to be merged
	 */
	T merge(T o);

	/**
	 * saves or update the object state in the database
	 * 
	 * @param object
	 *            object to be saved
	 */
	@Transactional
	void save(T o);
}
