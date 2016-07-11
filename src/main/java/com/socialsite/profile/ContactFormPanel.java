
package com.socialsite.profile;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import com.socialsite.BasePanel;
import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;
import com.socialsite.util.PrivacyField;
import com.socialsite.util.PrivacyModel;

public class ContactFormPanel extends BasePanel
{

	private final Profile profile;

	@SpringBean(name = "profileDao")
	private ProfileDao profileDao;

	/** feedback panel */
	FeedbackPanel feedback;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactFormPanel(final String id)
	{
		super(id);
		profile = getSessionUser().getProfile();
		setOutputMarkupId(true);
		final Form<Profile> form = new Form<Profile>("form", new CompoundPropertyModel<Profile>(
				profile));
		add(form);
		form.add(new RequiredTextField<String>("email").add(EmailAddressValidator.getInstance()));
		form.add(new PrivacyField("mobilePhone", new PropertyModel<PrivacyModel>(profile,
				"mobilePhone")));
		form.add(new PrivacyField("landPhone",
				new PropertyModel<PrivacyModel>(profile, "landPhone")));
		form.add(new PrivacyField("address", new PropertyModel<PrivacyModel>(profile, "address")));
		form.add(new TextField<String>("city"));
		form.add(new TextField<String>("neighborhood"));
		form.add(new TextField<Integer>("zip"));
		form.add(new TextField<String>("website"));

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
