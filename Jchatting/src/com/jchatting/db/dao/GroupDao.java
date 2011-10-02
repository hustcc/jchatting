/**
 * 
 */
package com.jchatting.db.dao;

import com.jchatting.db.bean.Group;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-2 下午08:58:25
 */
public interface GroupDao {
	public Group findById(String groupId);
	public int insert(Group group);
}
