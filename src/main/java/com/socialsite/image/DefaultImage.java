
package com.socialsite.image;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.wicket.markup.html.PackageResource;
import org.apache.wicket.util.io.IOUtils;
import org.apache.wicket.util.resource.ResourceStreamNotFoundException;

import com.socialsite.persistence.Course;
import com.socialsite.persistence.Profile;
import com.socialsite.persistence.University;


public class DefaultImage
{

	/**
	 * sets the default image for a course
	 * 
	 * @param course
	 *            course
	 */
	public void forCourse(final Course course)
	{
		course.changeImage(getImageData("course-125.png"));
		course.changeThumb(getImageData("course-75.png"));
	}

	/**
	 * sets the default image for a university
	 * 
	 * @param university
	 *            university
	 */
	public void forUniversity(final University university)
	{
		university.changeImage(getImageData("university-125.png"));
		university.changeThumb(getImageData("university-75.png"));
	}

	/**
	 * set the default image for a user
	 * 
	 * @param profile
	 *            profile
	 */
	public void forUser(final Profile profile)
	{
		profile.changeImage(getImageData("user-125.png"));
		profile.changeThumb(getImageData("user-75.png"));
	}

	/**
	 * gets the image as byte array
	 * 
	 * @param name
	 *            name of the image file
	 * @return byte array of the image
	 */
	public byte[] getImageData(final String name)
	{
		final PackageResource imageRef = PackageResource.get(getClass(), name);
		try
		{
			return IOUtils.toByteArray(imageRef.getResourceStream().getInputStream());
		}
		catch (final IOException e)
		{
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		}
		catch (final ResourceStreamNotFoundException e)
		{
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}

}
