
package com.socialsite.course;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;

import com.socialsite.BasePanel;
import com.socialsite.persistence.Staff;
import com.socialsite.persistence.User;


public class NewCoursePanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewCoursePanel(String id)
	{
		super(id);
		final ModalWindow courseModal;
		add(courseModal = new ModalWindow("coursemodal"));

		courseModal.setContent(new NewCourseModal(courseModal.getContentId()));
		courseModal.setTitle("Create new Course");
		courseModal.setCookieName("coursemodal");

		courseModal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean onCloseButtonClicked(AjaxRequestTarget target)
			{
				return true;
			}
		});

		courseModal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onClose(AjaxRequestTarget target)
			{

			}
		});

		add(new AjaxLink<Void>("newcourse")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target)
			{
				courseModal.show(target);
			}
		});
	}

	@Override
	public boolean isVisible()
	{
		if (!hasRole("OWNER"))
		{
			return false;
		}
		User user = getSessionUser();
		if (user instanceof Staff)
		{
			return (((Staff)user).getUniversity() != null);
		}
		return false;
	}

}
