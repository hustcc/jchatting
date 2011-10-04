/**
 * 
 */
package com.jchatting.db.dao;

import java.util.List;

import com.jchatting.db.bean.UserUser;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-25 下午04:07:28
 */
public interface UserUserDao {

	public List<UserUser> findAllByAccount(String account);
	
}
