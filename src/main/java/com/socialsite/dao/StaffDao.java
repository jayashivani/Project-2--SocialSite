
package com.socialsite.dao;

import java.util.List;

import com.socialsite.persistence.Staff;

public interface StaffDao extends UserDao<Staff>
{

	/**
	 * 
	 * gets the list of staffs in a university
	 * 
	 * @param university
	 * @param first
	 * @param count
	 * @return
	 */
	public List<Staff> getStaffs(final long universityId, final int first, final int count);
}
