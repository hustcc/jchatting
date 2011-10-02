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
}
