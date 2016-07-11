
package com.socialsite.course;

import com.socialsite.BasePage;


public class UserCoursesPage extends BasePage
{
	public UserCoursesPage()
	{
		add(new AllCoursesPanel("courses"));
	}
}
