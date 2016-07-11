

package com.socialsite.activation;

import java.util.Date;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.ActivationDao;
import com.socialsite.dao.UniversityDao;
import com.socialsite.image.DefaultImage;
import com.socialsite.persistence.Activation;
import com.socialsite.persistence.University;

public class ActivationPage extends WebPage
{

	@SuppressWarnings("unused")
	private String messageText;
	@SpringBean(name = "activationDao")
	private ActivationDao activationDao;
	@SpringBean(name = "universityDao")
	private UniversityDao universityDao;

	public ActivationPage(final PageParameters params)
	{

		add(new Label("message", new PropertyModel<String>(this, "messageText")));
		final long id = params.getAsLong("id", 0);
		if (id == 0)
		{
			messageText = "Access denied";
		}
		else
		{
			final String action = params.getString("action");
			final Activation activation = activationDao.load(id);
			if (activation != null)
			{
				if (action.equals("activate"))
				{
					createUniversity(activation);
					sendNotification(activation, true);
					messageText = "University Created";
				}
				else
				{
					sendNotification(activation, false);
					messageText = "University request rejected";
				}
				activationDao.delete(activation);
			}
			else
			{
				messageText = "activation id not found";
			}
		}
	}

	public void createUniversity(final Activation activation)
	{
		final University university = new University(activation.getUniversityName(), activation
				.getAdmin());
		university.setLastModified(new Date());
		final DefaultImage defaultImage = new DefaultImage();
		defaultImage.forUniversity(university);
		universityDao.save(university);
	}

	public void sendNotification(final Activation activation, final boolean accept)
	{
		// TODO send a notification message to the admin
	}
}
