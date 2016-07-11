
package com.socialsite.authentication;

import org.apache.wicket.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authorization.strategies.role.RoleAuthorizationStrategy;


public final class SocialSiteAuthorizationStrategy extends RoleAuthorizationStrategy
{

	public SocialSiteAuthorizationStrategy(final IRoleCheckingStrategy roleCheckingStrategy)
	{
		super(roleCheckingStrategy);

	}

}
