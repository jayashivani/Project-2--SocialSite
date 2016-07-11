
package com.socialsite.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmailSender
{
	private static final String ACTIVATE_URL = "http://email-relay.appspot.com/email_service?";
	private final static Logger logger = Logger.getLogger(EmailSender.class.getName());
	private final StringBuffer emailURL = new StringBuffer();

	public EmailSender()
	{
		// set up the proxy server
		final Properties props = System.getProperties();
		props.put("http.proxyHost", "proxy.karunya.edu");
		props.put("http.proxyPort", "3128");
	}

	public void addParam(final String key, final String value)
	{
		try
		{
			emailURL.append(key).append("=").append(URLEncoder.encode(value, "UTF-8")).append("&");
		}
		catch (final UnsupportedEncodingException e)
		{
			logger.log(Level.SEVERE, "", e);
		}
	}

	/**
	 * I cann't send email through smtp. So here i am sending mail through a
	 * proxy server. To send email through smtp server change this
	 * implementation
	 * 
	 * @param email
	 */
	public void send(final Email email)
	{
		// TODO this should run asynchronously
		// handle multiple receivers
		addParam("receiver", email.getReceivers().get(0));
		// someone is going to send email using this thing
		addParam("secret", "dontMESSwithme");
		addParam("msg", email.getMessage());
		addParam("subject", email.getSubject());


		try
		{

			final URL url = new URL(ACTIVATE_URL + emailURL.toString());
			final BufferedReader reader = new BufferedReader(
					new InputStreamReader(url.openStream()));
			String line;
			final StringBuffer reply = new StringBuffer();
			while ((line = reader.readLine()) != null)
			{
				reply.append(line);
			}
			reader.close();

			if (!reply.toString().equals("message send successfully"))
			{
				// message sending failed
				logger.severe("Could not send Email");
				logger.severe(reply.toString());
				logger.severe("URL" + ACTIVATE_URL + emailURL.toString());
			}
			else
			{
				logger.info("email send successfully");
			}

		}
		catch (final MalformedURLException e)
		{
			logger.log(Level.SEVERE, "", e);
		}
		catch (final IOException e)
		{
			logger.log(Level.SEVERE, "", e);
		}
	}
}
