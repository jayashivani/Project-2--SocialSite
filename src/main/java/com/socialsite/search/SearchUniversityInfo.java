
package com.socialsite.search;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.socialsite.dao.UniversityDao;
import com.socialsite.image.ImagePanel;
import com.socialsite.image.ImageType;
import com.socialsite.persistence.University;
import com.socialsite.university.UniversityLink;


public class SearchUniversityInfo extends Panel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@SpringBean(name = "universityDao")
	UniversityDao universityDao;

	/**
	 * 
	 * @param id
	 *            component id
	 * @param model
	 *            university model
	 */
	public SearchUniversityInfo(final String id, final IModel<University> model)
	{
		super(id);
		final University university = model.getObject();
		final UniversityLink universityLink;
		add(new ImagePanel("image", university.getId(), ImageType.UNIVERSITY, university
				.getLastModified(), false, false));
		add(universityLink = new UniversityLink("university", model));
		universityLink.add(new Label("name", university.getName()));
		add(new Label("students", universityDao.getStudentsCount(university.getId()) + ""));
		add(new Label("courses", university.getCourses().size() + ""));
	}
}
