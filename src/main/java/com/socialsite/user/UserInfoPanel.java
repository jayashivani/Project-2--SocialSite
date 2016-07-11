
package com.socialsite.user;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.SocialSiteSession;
import com.socialsite.course.NewCoursePanel;
import com.socialsite.dao.ProfileDao;
import com.socialsite.dao.UserDao;
import com.socialsite.friend.AddAsFriendPanel;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageService;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.User;


public class UserInfoPanel extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** spring dao to handle user object */
	@SpringBean(name = "userDao")
	private UserDao<User> userDao;

	/** spring dao to handle profile object */
	@SpringBean(name = "profileDao")
	private ProfileDao profileDao;


	public UserInfoPanel(final String id)
	{
		super(id);

		final User user = userDao.load(SocialSiteSession.get().getUserId());

		add(new ImagePanel("userimage", user.getId(), ImageType.USER, user.getLastModified())
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void saveImage(final byte[] imageData)
			{
				final ImageService imageService = new ImageService();

				user.getProfile().changeThumb(
						imageService.resize(imageData, ImageService.THUMB_SIZE));
				user.getProfile().changeImage(
						imageService.resize(imageData, ImageService.IMAGE_SIZE));
				profileDao.save(user.getProfile());
				userDao.save(user);

			}
		});

		final Profile profile = user.getProfile();

		add(new Label("username", user.getUserName()));
		String city = "";
		if (profile.getCurrentCity() != null)
		{
			city = profile.getCurrentCity().getValue();
		}
		add(new Label("city", city));
		add(new Label("status", profile.getRelationshipStatus()));
		add(new Label("aboutme", profile.getAboutMe()));
		add(new AddAsFriendPanel("addasfriend"));
		add(new NewCoursePanel("addnewcourse"));
	}
}
