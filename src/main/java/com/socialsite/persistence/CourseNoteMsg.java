
package com.socialsite.persistence;


public class CourseNoteMsg extends Message
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Note note;


	public CourseNoteMsg()
	{
		// TODO Auto-generated constructor stub
	}


	public void setNote(Note note)
	{
		this.note = note;
	}


	public Note getNote()
	{
		return note;
	}

}
