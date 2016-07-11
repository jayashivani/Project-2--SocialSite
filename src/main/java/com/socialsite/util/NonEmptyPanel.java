
package com.socialsite.util;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;


public class NonEmptyPanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String value;

	public NonEmptyPanel(final String id, final String label, final String value)
	{
		super(id);
		this.value = value;
		add(new Label("label", label));
		add(new Label("value", value));
	}

	@Override
	public boolean isVisible()
	{
		if (value == null || value.equals(""))
		{
			return false;
		}
		return true;
	}


}
