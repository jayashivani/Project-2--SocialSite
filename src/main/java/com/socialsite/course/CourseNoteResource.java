
package com.socialsite.course;

import org.apache.wicket.RequestCycle;
import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.value.ValueMap;

import com.socialsite.dao.NoteDao;
import com.socialsite.persistence.Note;

public class CourseNoteResource extends DynamicWebResource
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SpringBean(name = "noteDao")
	NoteDao noteDao;

	@Override
	protected ResourceState getResourceState()
	{

		// parameters
		final ValueMap params = getParameters();
		final long id = params.getAsLong("id");
		final Note note = getNoteDao().load(id);

		WebResponse r = (WebResponse)RequestCycle.get().getResponse();
		r.setAttachmentHeader(note.getFileName());

		return new ResourceState()
		{

			@Override
			public byte[] getData()
			{
				return note.getData();
			}

			@Override
			public String getContentType()
			{
				return note.getContentType();
			}
		};

	}

	public NoteDao getNoteDao()
	{
		if (noteDao == null)
		{
			// inject the spring dao
			InjectorHolder.getInjector().inject(this);
		}

		return noteDao;
	}
}
