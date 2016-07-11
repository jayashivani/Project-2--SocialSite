
package com.socialsite.dao;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.socialsite.persistence.Student;
import com.socialsite.persistence.University;

public interface UniversityDao extends AbstractImageDao<University>
{
	/**
	 * find the number of unique rows in the University (table) that matched the
	 * search text (filter)
	 * 
	 * @param filter
	 *            search text
	 * @return no of unique rows in the University (table) that matched the
	 *         search text (filter)
	 */
	public int countAll(String filter);

	/**
	 * find the list of all university according to the search text(filter) and
	 * orders the list according to the sortParam
	 * 
	 * @param filter
	 *            filter for university name
	 *            <p>
	 *            if filter is null all the users will be added to the list
	 * @param first
	 *            first item no of the university List
	 * @param count
	 *            no of items needed in the university List
	 * @param sortParam
	 *            used to sort the university List
	 * @return List containing the {@link University} matched the search
	 *         criteria
	 */
	public List<University> findAll(String filter, int first, int count, SortParam sortParam);

	/**
	 * list of all the students in the university
	 * 
	 * @param id
	 * @return
	 */
	public List<Student> getStudents(long id);

	/**
	 * get the student list in the university
	 * 
	 * @param id
	 * @param first
	 * @param count
	 * @return
	 */
	public List<Student> getStudents(long id, int first, int count);

	/**
	 * get the students count
	 * 
	 * @param id
	 * @return
	 */
	public int getStudentsCount(long id);
}
