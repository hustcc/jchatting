/**
 * 
 */
package com.jchatting.db.dao;

import java.util.Map;

import com.jchatting.db.bean.User;
import com.jchatting.db.bean.UserInGroup;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-2 下午03:10:29
 */
public interface UserInGroupDao {

	public Map<String, UserInGroup> findAllGroupOfUser(User user);
	public int addUserIntoGroup(User user, String groupId);
	/**
	 * 得到某一个群中的用户数目
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-3 下午12:59:29
	 * @param groupId
	 * @return
	 */
	public int getGroupUserCount(String groupId);
}
