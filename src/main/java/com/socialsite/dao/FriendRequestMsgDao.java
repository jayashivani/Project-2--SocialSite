package com.socialsite.dao;

import com.socialsite.persistence.FriendRequestMsg;

public interface FriendRequestMsgDao extends MessageDao<FriendRequestMsg>
{
	public boolean hasFriendRequest(long userid, long guestid);
}
