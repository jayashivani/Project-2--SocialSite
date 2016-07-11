
package com.socialsite.profile;

import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.BasePanel;

public class ContactTabPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private final Panel infoPanel = new ContactInfoPanel("contact");
	private final Panel formPanel = new ContactFormPanel("contact");
	private final Panel current = infoPanel;

	public ContactTabPanel(final String id)
	{
		super(id);
		add(current);
		add(new EditLink("edit", infoPanel, formPanel, getRoles()));
	}


}
