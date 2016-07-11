
package com.socialsite.profile;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;

public class EducationFormPanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Profile profile;

	@SpringBean(name = "profileDao")
	private ProfileDao profileDao;

	/** feedback panel */
	FeedbackPanel feedback;


	public EducationFormPanel(final String id)
	{
		super(id);
		profile = getSessionUser().getProfile();
		setOutputMarkupId(true);
		final Form<Profile> form = new Form<Profile>("form", new CompoundPropertyModel<Profile>(
				profile));
		add(form);
		form.add(new TextField<String>("college"));

		form.add(new AjaxSubmitLink("save")
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;


			@Override
			protected void onError(final AjaxRequestTarget target, final Form<?> form)
			{
				super.onError(target, form);
				// show feedback messages
				target.addComponent(feedback);
			}

			@Override
			protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
			{
				profileDao.save(profile);
				target.appendJavascript("SocialSite.Message.show('profile updated')");
				// remove old feedback message
				target.addComponent(feedback);
			}
		});

		add(feedback = new FeedbackPanel("feedback"));
		feedback.setOutputMarkupId(true);
	}

}
