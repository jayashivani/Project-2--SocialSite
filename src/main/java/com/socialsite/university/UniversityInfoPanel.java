
package com.socialsite.university;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.BasePanel;
import com.socialsite.dao.UniversityDao;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageService;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.University;
import com.socialsite.persistence.User;
import com.socialsite.user.UserLink;


public class UniversityInfoPanel extends BasePanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@SpringBean(name = "universityDao")
	private UniversityDao universityDao;

	/**
	 * constructor
	 * 
	 * @param id
	 *            component id
	 */
	public UniversityInfoPanel(final String id, final University university)
	{
		super(id);
		// image of the university
		add(new ImagePanel("image", university.getId(), ImageType.UNIVERSITY, university
				.getLastModified())
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void saveImage(final byte[] imageData)
			{
				final ImageService imageService = new ImageService();
				university.changeImage(imageService.resize(imageData, ImageService.IMAGE_SIZE));
				university.changeThumb(imageService.resize(imageData, ImageService.THUMB_SIZE));
				universityDao.save(university);
			}

		});

		// admin of the university
		add(new UserLink<User>("admin", new Model<User>(university.getAdmin())));
		add(new JoinUniversityPanel("join", new Model<University>(university)));
		add(new Label("courses", university.getCourses().size() + ""));
		add(new Label("staffs", university.getStaffs().size() + ""));

	}
}
