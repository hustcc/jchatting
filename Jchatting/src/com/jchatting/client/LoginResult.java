/**
 * 
 */
package com.jchatting.client;

import com.jchatting.db.bean.User;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午07:57:12
 */
public class LoginResult {

	private int returnValue;
	private User user;
	/**
	 * 
	 */
	public LoginResult() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the returnValue
	 */
	public int getReturnValue() {
		return returnValue;
	}
	/**
	 * @param returnValue the returnValue to set
	 */
	public void setReturnValue(int returnValue) {
		this.returnValue = returnValue;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
