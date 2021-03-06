
package com.socialsite.persistence;

import java.util.HashSet;
import java.util.Set;

public class Staff extends User
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private University university;

	private Set<Course> courses = new HashSet<Course>();

	public Staff()
	{
	}

	public Staff(final String userName, final String password)
	{
		super(userName, password);
	}

	public Staff(final String userName, final String password, final University university)
	{
		super(userName, password);
		setUniversity(university);
	}

	public void addCourse(final Course course)
	{
		getCourses().add(course);
		getUniversity().getCourses().add(course);
		course.setStaff(this);
	}

	public void addUniversity(final University university)
	{
		setUniversity(university);
		university.getStaffs().add(this);
	}

	public Set<Course> getCourses()
	{
		return courses;
	}

	public University getUniversity()
	{
		return university;
	}

	public void setCourses(final Set<Course> courses)
	{
		this.courses = courses;
	}

	public void setUniversity(final University university)
	{
		this.university = university;
	}
}
