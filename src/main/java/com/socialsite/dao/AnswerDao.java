
package com.socialsite.dao;

import java.util.List;

import com.socialsite.persistence.Answer;


public interface AnswerDao extends AbstractDao<Answer>
{
	/**
	 * gets the list of answer in the given question
	 * 
	 * @param questionId
	 *            question id
	 * @param first
	 *            first result index
	 * @param count
	 *            count of results
	 * @return list of answers
	 */
	public List<Answer> getAnswers(long questionId, int first, int count);

	/**
	 * gets the count of answer in the given question
	 * 
	 * @param questionId
	 *            question id
	 * @return count of answers
	 */
	public int getAnswersCount(long questionId);
}
