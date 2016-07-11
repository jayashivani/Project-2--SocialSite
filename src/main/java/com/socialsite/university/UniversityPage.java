
package com.socialsite.university;

import com.socialsite.BasePage;
import com.socialsite.course.CoursesPanel;
import com.socialsite.dataprovider.UniversityCourseDataProvider;
import com.socialsite.persistence.University;
import com.socialsite.staff.StaffsPanel;


public class UniversityPage extends BasePage
{

	/**
	 * constructor
	 */
	public UniversityPage(final University university)
	{
		add(new UniversityInfoPanel("info", university));
		add(new CoursesPanel("courses", new UniversityCourseDataProvider(university.getId())));
		add(new StaffsPanel("staffs", university));
	}

}
