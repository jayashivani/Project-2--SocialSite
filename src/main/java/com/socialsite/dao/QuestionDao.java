
package com.socialsite.dao;

import java.util.List;

import com.socialsite.persistence.Question;


public interface QuestionDao extends AbstractDao<Question>
{
	/**
	 * gets the list of questions
	 * 
	 * @param courseId
	 *            course id
	 * @param first
	 *            first result index
	 * @param count
	 *            count of results
	 * @return list of questions
	 */
	public List<Question> getQuestions(long courseId, int first, int count);

	/**
	 * gets the count of questions
	 * 
	 * @param courseId
	 *            course id
	 * @return count of questions
	 */
	public int getQuestionsCount(long courseId);
}
