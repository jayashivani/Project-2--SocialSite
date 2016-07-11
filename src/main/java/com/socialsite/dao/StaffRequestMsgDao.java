
package com.socialsite.dao;

import com.socialsite.persistence.Staff;
import com.socialsite.persistence.StaffRequestMsg;

public interface StaffRequestMsgDao extends MessageDao<StaffRequestMsg>
{
	/**
	 * checks whether the staff had sent any request to any university
	 * 
	 * @param staff
	 * @param university
	 * @return
	 */
	public boolean hasRequest(Staff staff);
}
