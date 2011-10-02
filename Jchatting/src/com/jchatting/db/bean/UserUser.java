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
public class UserUser implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String TABLE_NAME = "user_user";
	
	public final static String ID = "id";
	public final static String ACCOUNT_USER = "account_user";
	public final static String ACCOUNT_FRIEND = "account_friend";
	public final static String ATTACH = "attach";
	public final static String MEMO = "memo";
	
	private int id;
	private String accountUser;
	private String accountFriend;
	private String attach;
	private String memo;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the accountUser
	 */
	public String getAccountUser() {
		return accountUser;
	}
	/**
	 * @param accountUser the accountUser to set
	 */
	public void setAccountUser(String accountUser) {
		this.accountUser = accountUser;
	}
	/**
	 * @return the accountFriend
	 */
	public String getAccountFriend() {
		return accountFriend;
	}
	/**
	 * @param accountFriend the accountFriend to set
	 */
	public void setAccountFriend(String accountFriend) {
		this.accountFriend = accountFriend;
	}
	/**
	 * @return the attach
	 */
	public String getAttach() {
		return attach;
	}
	/**
	 * @param attach the attach to set
	 */
	public void setAttach(String attach) {
		this.attach = attach;
	}
	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getAttach();
	}
	
	
}
