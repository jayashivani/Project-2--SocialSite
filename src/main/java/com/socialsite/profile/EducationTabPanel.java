
package com.socialsite.profile;

import org.apache.wicket.markup.html.panel.Panel;

import com.socialsite.BasePanel;


public class EducationTabPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Panel infoPanel = new EducationInfoPanel("education");
	private final Panel formPanel = new EducationFormPanel("education");
	private final Panel current = infoPanel;


	public EducationTabPanel(final String id)
	{
		super(id);
		add(current);
		add(new EditLink("edit", infoPanel, formPanel, getRoles()));
	}

}
