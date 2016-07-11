
package com.socialsite.profile;

import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.BasePanel;

public class BasicTabPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Panel infoPanel = new BasicInfoPanel("basic");
	private final Panel formPanel = new BasicFormPanel("basic");
	private final Panel current = infoPanel;


	public BasicTabPanel(final String id)
	{
		super(id);
		add(current);
		add(new EditLink("edit", infoPanel, formPanel, getRoles()));
	}

}
