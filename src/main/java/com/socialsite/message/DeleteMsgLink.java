
package com.socialsite.message;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.SocialSiteSession;
import com.socialsite.authentication.SocialSiteRoles;
import com.socialsite.dao.MessageDao;
import com.socialsite.dao.UserDao;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.User;


public class DeleteMsgLink<T extends Message> extends AjaxLink<T>
{

	/** spring dao to handle message object */
	@SpringBean(name = "messageDao")
	private MessageDao<Message> messageDao;

	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	MarkupContainer dependent;
	BasePanel panel;
	long senderId;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeleteMsgLink(final String id, final IModel<T> model, final MarkupContainer dependent,
			final BasePanel panel, final long senderId)
	{
		super(id, model);
		this.dependent = dependent;
		this.panel = panel;
		this.senderId = senderId;
	}

	@Override
	public boolean isVisible()
	{
		// allow the owner and the sender to delete the msg
		if (panel.hasRole(SocialSiteRoles.OWNER))
		{
			return true;
		}
		else
		{
			return senderId == panel.getSessionUserId();
		}
	}

	@Override
	public void onClick(final AjaxRequestTarget target)
	{

		Message msg = messageDao.load(getModelObject().getId());
		User user = userDao.load((SocialSiteSession.get().getUserId()));
		msg.removeUser(user);
		messageDao.save(msg);
		target.addComponent(dependent);
		panel.firePostAjaxUpdateEvent(target);
	}
}
