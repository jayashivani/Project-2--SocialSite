
package com.socialsite.message;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import com.socialsite.SocialSiteSession;
import com.socialsite.dataprovider.MessageDataProvider;
import com.socialsite.persistence.CourseJoinedMsg;
import com.socialsite.persistence.CourseNoteMsg;
import com.socialsite.persistence.FriendRequestMsg;
import com.socialsite.persistence.InfoMsg;
import com.socialsite.persistence.Message;
import com.socialsite.persistence.QuestionInfoMsg;
import com.socialsite.persistence.StaffRequestMsg;


public class MessagePanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** container for the messages */
	WebMarkupContainer messageContainer;

	/**
	 * constructor
	 * 
	 * @param id
	 *            id
	 */
	public MessagePanel(final String id)
	{
		super(id);

		// container
		add(messageContainer = new WebMarkupContainer("container"));
		messageContainer.setOutputMarkupId(true);

		final MessageDataProvider MessageDataProvider = new MessageDataProvider(SocialSiteSession
				.get().getUserId());

		final DataView<Message> friendRequestDataView = new DataView<Message>("messagelist",
				MessageDataProvider)
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final Item<Message> item)
			{

				final Message message = item.getModelObject();
				if (message instanceof FriendRequestMsg)
				{
					item.add(new FriendRequestMsgPanel("message", (FriendRequestMsg)message,
							messageContainer));
				}
				else if (message instanceof InfoMsg)
				{
					item.add(new InfoMsgPanel("message", new Model<InfoMsg>(((InfoMsg)message)),
							messageContainer));
				}
				else if (message instanceof CourseJoinedMsg)
				{
					item.add(new CourseJoinedMsgPanel("message", new Model<CourseJoinedMsg>(
							(CourseJoinedMsg)message), messageContainer));
				}
				else if (message instanceof StaffRequestMsg)
				{
					item.add(new StaffRequestMsgPanel("message", new Model<StaffRequestMsg>(
							(StaffRequestMsg)message), messageContainer));
				}
				else if (message instanceof CourseNoteMsg)
				{
					item.add(new CourseNoteMsgPanel("message", new Model<CourseNoteMsg>(
							(CourseNoteMsg)message), messageContainer));
				}
				else if (message instanceof QuestionInfoMsg)
				{
					item.add(new QuestionInfoMsgPanel("message", new Model<QuestionInfoMsg>(
							(QuestionInfoMsg)message), messageContainer));
				}
				else
				{
					Logger.getLogger(getClass().getName()).log(Level.SEVERE,
							message.getClass().getName());
				}
			}
		};

		messageContainer.add(friendRequestDataView);

	}

}
