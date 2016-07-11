
package com.socialsite.dao;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

import com.socialsite.persistence.Course;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;


public interface CourseDao extends AbstractImageDao<Course>
{
	/**
	 * find the number of unique rows in the Course (table) that matched the
	 * search text (filter)
	 * 
	 * @param filter
	 *            search text
	 * @return no of unique rows in the Course (table) that matched the search
	 *         text (filter)
	 */
	public int countAll(String filter);

	/**
	 * find the list of all Course according to the search text(filter) and
	 * orders the list according to the sortParam
	 * 
	 * @param filter
	 *            filter for course name
	 *            <p>
	 *            if filter is null all the users will be added to the list
	 * @param first
	 *            first item no of the course List
	 * @param count
	 *            no of items needed in the course List
	 * @param sortParam
	 *            used to sort the course List
	 * @return List containing the {@link University} matched the search
	 *         criteria
	 */
	public List<Course> findAll(String filter, int first, int count, SortParam sortParam);


	/**
	 * get the courses
	 * 
	 * @param id
	 *            user id
	 * @param first
	 * @param count
	 * @return
	 */
	public List<Course> getUserCourses(long id, int first, int count);

	/**
	 * get the courses
	 * 
	 * @param id
	 *            university id
	 * @param first
	 * @param count
	 * @return
	 */
	public List<Course> getUniversityCourses(long id, int first, int count);


	/**
	 * count of courses
	 * 
	 * @param id
	 *            user id
	 * @return
	 */
	public int getUserCoursesCount(long id);


	/**
	 * count of courses
	 * 
	 * @param id
	 *            university id
	 * @return
	 */
	public int getUniversityCoursesCount(long id);

	/**
	 * list of students in the course
	 * 
	 * @param id
	 * @param first
	 * @param count
	 * @return
	 */
	public List<User> getStudents(long id, int first, int count);

	/**
	 * count of students in the course
	 * 
	 * @param id
	 * @return
	 */
	public int getStudentsCount(long id);

}