
package com.socialsite.profile;

import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.BasePanel;

public class PersonalTabPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Panel infoPanel = new PersonalInfoPanel("personal");
	private final Panel formPanel = new PersonalFormPanel("personal");
	private final Panel current = infoPanel;

	public PersonalTabPanel(final String id)
	{
		super(id);
		add(current);
		add(new EditLink("edit", infoPanel, formPanel, getRoles()));
	}
}
