
package com.socialsite.persistence;


public class QuestionInfoMsg extends Message
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Question question;

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

}
