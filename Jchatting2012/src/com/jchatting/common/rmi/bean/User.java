/**
 * 保存登录用的帐号密码信息
 */
package com.jchatting.common.rmi.bean;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2012-2-24 下午3:08:36
 */
public class User {

	private String userId;
	private String password;
	
	public User(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
