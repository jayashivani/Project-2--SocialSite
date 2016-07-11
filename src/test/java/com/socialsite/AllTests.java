
package com.socialsite;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.socialsite.authentication.LoginPageTest;
import com.socialsite.authentication.SignUpPageTest;
import com.socialsite.dao.hibernate.ActivationDaoTest;
import com.socialsite.dao.hibernate.AdminDaoTest;
import com.socialsite.dao.hibernate.AnswerDaoTest;
import com.socialsite.dao.hibernate.CommentDaoTest;
import com.socialsite.dao.hibernate.CourseDaoTest;
import com.socialsite.dao.hibernate.FriendRequestMsgDaoTest;
import com.socialsite.dao.hibernate.InfoMsgDaoTest;
import com.socialsite.dao.hibernate.MessageDaoTest;
import com.socialsite.dao.hibernate.ProfileDaoTest;
import com.socialsite.dao.hibernate.QuestionDaoTest;
import com.socialsite.dao.hibernate.StaffDaoTest;
import com.socialsite.dao.hibernate.StaffRequestMsgDaoTest;
import com.socialsite.dao.hibernate.StudentDaoTest;
import com.socialsite.dao.hibernate.UniversityDaoTest;
import com.socialsite.dao.hibernate.UserDaoTest;
import com.socialsite.scripts.SchemaCreator;

@RunWith(Suite.class)
@Suite.SuiteClasses( { LoginPageTest.class, SignUpPageTest.class, UserDaoTest.class,
		ProfileDaoTest.class, FriendRequestMsgDaoTest.class, UniversityDaoTest.class,
		CourseDaoTest.class, AdminDaoTest.class, AnswerDaoTest.class, CommentDaoTest.class,
		InfoMsgDaoTest.class, MessageDaoTest.class, QuestionDaoTest.class, StaffDaoTest.class,
		StudentDaoTest.class, ActivationDaoTest.class, StaffRequestMsgDaoTest.class })
public class AllTests
{

	@BeforeClass
	public static void setup()
	{
		// clean the database before running the tests
		SchemaCreator.create();
	}
	// run thisclass to run all the test
}
