
package com.socialsite.university;

import java.util.Date;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.StaffRequestMsgDao;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.StaffRequestMsg;
import com.socialsite.persistence.University;


public class JoinUniversityPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "staffRequestMsgDao")
	private StaffRequestMsgDao staffRequestMsgDao;

	/** specifies the visibility */
	private Boolean isVisible = null;

	private final University university;

	public JoinUniversityPanel(final String id, final IModel<University> model)
	{
		super(id);
		setOutputMarkupId(true);
		university = model.getObject();
		add(new AjaxLink<Void>("join")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				final Staff staff = (Staff)getSessionUser();
				final StaffRequestMsg msg = new StaffRequestMsg(staff);
				msg.setTime(new Date());
				msg.addUser(university.getAdmin());
				msg.setUniversity(university);
				staffRequestMsgDao.save(msg);
				isVisible = false;
				target.addComponent(JoinUniversityPanel.this);
				target
						.appendJavascript("SocialSite.Message.show('Your request has been sent to the admin');");
			}
		});
	}

	@Override
	public boolean isVisible()
	{
		// check the first time only
		if (isVisible == null)
		{
			if (getSessionUser() instanceof Staff)
			{
				final Staff staff = (Staff)getSessionUser();
				if (staff.getUniversity() != null)
				{
					isVisible = false;
				}
				else
				{
					isVisible = !staffRequestMsgDao.hasRequest(staff);
				}
			}
			else
			{
				isVisible = false;
			}
		}
		return isVisible;
	}
}
