

package com.socialsite.profile;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.ProfileDao;
import com.socialsite.persistence.Profile;
import com.socialsite.util.PrivacyField;
import com.socialsite.util.PrivacyModel;

/**
 * 
 */
public class BasicFormPanel extends BasePanel
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

	public BasicFormPanel(final String id)
	{
		super(id);
		profile = getSessionUser().getProfile();
		setOutputMarkupId(true);
		final Form<Profile> form = new Form<Profile>("form", new CompoundPropertyModel<Profile>(
				profile));
		add(form);
		form.add(new TextField<String>("firstName"));
		form.add(new TextField<String>("lastName"));
		form.add(new DropDownChoice<String>("sex", Arrays.asList("Male", "Female")));
		form.add(new PrivacyField("currentCity", new PropertyModel<PrivacyModel>(profile,
				"currentCity")));
		form
				.add(new PrivacyField("homeTown", new PropertyModel<PrivacyModel>(profile,
						"homeTown")));
		form.add(new DropDownChoice<String>("relationshipStatus", Arrays.asList("Single",
				"In a relationship", "Engaged", "Married", "Its complicated",
				"In a open relationship", "Widowed")));
		form.add(new TextField<String>("politicalView"));
		form.add(new TextField<String>("religiousView"));
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
