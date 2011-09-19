/**
 * 
 */
package com.jchatting.db.bean;

import java.io.Serializable;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 下午3:08:02
 *
 */
public class UserGroup implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int groupId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
