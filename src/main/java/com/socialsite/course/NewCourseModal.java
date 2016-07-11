package com.socialsite.course;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.CourseDao;
import com.socialsite.dao.StaffDao;
import com.socialsite.image.DefaultImage;
import com.socialsite.persistence.Course;
import com.socialsite.persistence.Staff;

public class NewCourseModal extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	/** feedback panel */
	private FeedbackPanel feedback;

	@SpringBean(name = "courseDao")
	CourseDao courseDao;

	@SpringBean(name = "staffDao")
	StaffDao staffDao;

	public NewCourseModal(String id)
	{
		super(id);

		Form<Void> form = new Form<Void>("form");
		add(form);

		form.add(new RequiredTextField<String>("name", new PropertyModel<String>(this, "name")));
		form.add(new RequiredTextField<String>("description", new PropertyModel<String>(this,
				"description")));

		form.add(new AjaxSubmitLink("create")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{

				Staff staff = staffDao.load(getSessionUserId());

				Course course = new Course();
				final DefaultImage image = new DefaultImage();
				image.forCourse(course);
				course.setLastModified(new Date());
				course.setName(name);
				course.setDescription(description);
				course.setUniversity(staff.getUniversity());
				course.setStaff(staff);


				courseDao.save(course);

				SocialSiteSession.get().getSessionUser().setRoles(SocialSiteRoles.staffRole);

				setResponsePage(new CoursePage(new Model<Course>(course)));
			}

			@Override
			protected void onError(AjaxRequestTarget target, Form<?> form)
			{
				target.addComponent(feedback);
			}

		});
		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
	}

}
