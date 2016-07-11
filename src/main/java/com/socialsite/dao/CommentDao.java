
package com.socialsite.dao;

import java.util.List;

import com.socialsite.persistence.Comment;


public interface CommentDao extends AbstractDao<Comment>
{
	/**
	 * gets the list of comments
	 * 
	 * @param answerId
	 *            answer id
	 * @param first
	 *            start index
	 * @param count
	 *            count
	 * @return list of comment associated with a answer
	 */
	public List<Comment> getComments(long answerId, int first, int count);

	/**
	 * gets the count to comment
	 * 
	 * @param answerId
	 *            answer id
	 * @return count of comments associated with a answer
	 */
	public int getCommentsCount(long answerId);
}
