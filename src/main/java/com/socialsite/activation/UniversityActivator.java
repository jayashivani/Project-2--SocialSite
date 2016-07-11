
package com.socialsite.activation;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.ActivationDao;
import com.socialsite.email.Email;
import com.socialsite.email.EmailSender;
import com.socialsite.persistence.Activation;
import com.socialsite.persistence.Admin;


public class UniversityActivator
{
	private final Admin admin;
	private final String universityName;
	private long activationId;

	@SpringBean(name = "activationDao")
	ActivationDao activationDao;

	public UniversityActivator(final Admin admin, final String universityName)
	{
		this.admin = admin;
		this.universityName = universityName;
		InjectorHolder.getInjector().inject(this);
	}

	public long addActivation()
	{
		final Activation activation = new Activation();
		activation.setAdmin(admin);
		activation.setUniversityName(universityName);
		activationDao.save(activation);
		return activation.getId();
	}

	public void create()
	{
		activationId = addActivation();
		// send email to the admin
		sendEmail();
	}

	public String getActivationUrl()
	{
		return "http://localhost:8098/activate?id=" + activationId + "&action=activate";
	}

	public String getDeActivationUrl()
	{
		return "http://localhost:8098/activate?id=" + activationId + "&action=deactivate";
	}

	public String getMessage()
	{
		final StringBuffer message = new StringBuffer();
		message.append("University Activation Request \n");
		message.append("UniversityName : ").append(universityName).append("\n");
		message.append("Admin Name :").append(admin.getUserName()).append("\n");
		// message.append("Admin email :").append(admin.getProfile().getEmail()).append("\n");
		message.append("To activate the university click the following url ").append(
				getActivationUrl()).append("\n");
		message.append("To Deactivate the university click the following url ").append(
				getDeActivationUrl()).append("\n");
		return message.toString();
	}

	/**
	 * sends email to the admin
	 */
	public void sendEmail()
	{
		final Email email = new Email();
		// am i sending message to myself :)
		email.addReceivers("shivani.jaya@gmail.com");
		email.setSubject("University Activation Request");
		email.setMessage(getMessage());
		new EmailSender().send(email);
	}
}
