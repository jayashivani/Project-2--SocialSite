package com.socialsite.image;

import java.util.Date;

import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.BasePanel;
import com.socialsite.ajax.fileupload.UploadPanel;
import com.socialsite.authentication.SocialSiteRoles;


public class ImagePanel extends BasePanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final private boolean changeLink;

	/**
	 * sets the thumnail as false
	 * 
	 * @param component
	 *            component id
	 * @param id
	 *            id used to fetch the image
	 * @param imageType
	 *            type of the image (userimage , courseimage etc)
	 * @param lastModified
	 *            lastmodified date of the image
	 */
	public ImagePanel(final String component, final long id, final ImageType imageType,
			final Date lastModified)
	{
		this(component, id, imageType, lastModified, false, true);
	}


	public ImagePanel(final String component, final long id, final ImageType imageType,
			final Date lastModified, final boolean thumb)
	{
		this(component, id, imageType, lastModified, thumb, false);
	}

	/**
	 * 
	 * @param component
	 *            component id
	 * @param id
	 *            id used to fetch the image
	 * @param imageType
	 *            type of the image (userimage , courseimage etc)
	 * @param thumb
	 *            will show thumb image if true
	 * @param lastModified
	 *            lastmodified date of the image
	 */
	public ImagePanel(final String component, final long id, final ImageType imageType,
			final Date lastModified, final boolean thumb, final boolean changeLink)
	{
		super(component);


		this.changeLink = changeLink;

		// allow the modal window to update the panel
		setOutputMarkupId(true);
		final ResourceReference imageResource = new ResourceReference(imageType.name());
		final Image userImage;
		final ValueMap valueMap = new ValueMap();
		valueMap.add("id", id + "");

		// the version is used to change the url dynamically if the image is
		// changed. This will allow the browser to cache images
		// reference http://code.google.com/speed/page-speed/docs/caching.html
		// #Use fingerprinting to dynamically enable caching.
		valueMap.add("version", lastModified.getTime() + "");
		if (thumb)
		{
			valueMap.add("thumb", "true");
		}
		add(userImage = new Image("userimage", imageResource, valueMap));
		userImage.setOutputMarkupId(true);

		final ModalWindow modal;
		add(modal = new ModalWindow("modal"));

		modal.setContent(new UploadPanel(modal.getContentId())
		{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String onFileUploaded(final FileUpload upload)
			{

				if (upload == null || upload.getSize() == 0)
				{
					// No image was provided
					error("Please upload an image.");
				}
				else if (!checkContentType(upload.getContentType()))
				{
					error("Only images of types png, jpg, and gif are allowed.");
				}
				else
				{
					saveImage(upload.getBytes());
				}

				return null;
			}

			@Override
			public void onUploadFinished(final AjaxRequestTarget target, final String filename,
					final String newFileUrl)
			{

				final ResourceReference imageResource = new ResourceReference(imageType.name());


				final ValueMap valueMap = new ValueMap();
				valueMap.add("id", id + "");
				// change the image lively
				valueMap.add("version", new Date().getTime() + "");
				if (thumb)
				{
					valueMap.add("thumb", "true");
				}

				userImage.setImageResourceReference(imageResource, valueMap);
				// update the image after the user changes it
				target.addComponent(userImage);
			}
		});
		modal.setTitle(" Select the image ");
		modal.setCookieName("modal");

		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean onCloseButtonClicked(final AjaxRequestTarget target)
			{
				return true;
			}
		});

		modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onClose(final AjaxRequestTarget target)
			{
			}
		});

		add(new AjaxLink<Void>("changeimage")
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isVisible()
			{
				if (changeLink)
				{
					// TODO allow admins to change the university image and
					// allow
					// staffs to change the course image
					// it. don't show it for thumb images
					return hasRole(SocialSiteRoles.OWNER) || hasRole(SocialSiteRoles.STAFF);
				}
				return false;

			}

			@Override
			public void onClick(final AjaxRequestTarget target)
			{
				modal.show(target);
			}
		});
	}

	private boolean checkContentType(final String contentType)
	{
		if (contentType.equalsIgnoreCase("image/gif") || contentType.equalsIgnoreCase("image/jpeg")
				|| contentType.equalsIgnoreCase("image/png"))
		{
			return true;
		}
		return false;
	}

	/**
	 * override this method to save the file
	 * 
	 * @param imageData
	 */
	protected void saveImage(final byte[] imageData)
	{

	}
}
