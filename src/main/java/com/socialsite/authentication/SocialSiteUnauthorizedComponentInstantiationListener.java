
package com.socialsite.authentication;

import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;


public class SocialSiteUnauthorizedComponentInstantiationListener
		implements
			IUnauthorizedComponentInstantiationListener
{
	/**
	 * Redirects to the LogingPage if the user is not authorized
	 * 
	 * @param component
	 *            component which failed the authorization check
	 */
	public void onUnauthorizedInstantiation(final Component component)
	{
		throw new RestartResponseAtInterceptPageException(LoginPage.class);

	}

}
