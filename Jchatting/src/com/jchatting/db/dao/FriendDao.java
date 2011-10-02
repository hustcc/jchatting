/**
 * 
 */
package com.jchatting.db.dao;

import java.util.Map;
import java.util.Vector;

import com.jchatting.db.bean.Friend;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午06:45:01
 */
public interface FriendDao {
	/**
	 * 获得对用某一个account的所有好友
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-28 下午06:45:44
	 * @param account
	 * @return
	 */
	public Vector<Friend> getAllFriendListByAccount(String account);
	/**
	 * 获得对用某一个account的所有好友
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-30 下午07:34:38
	 * @param account
	 * @return
	 */
	public Map<String, Friend> getAllFriendMapByAccount(String account);
}
