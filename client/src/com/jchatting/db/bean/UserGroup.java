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
	
	public static final String TABLE_NAME = "user_group";

	public static final String ID = "id";
	public static final String ACCOUNT = "account";
	public static final String GROUP_ID = "group_id";
	public static final String ATTACH = "attach";
	public static final String MEMO = "memo";
	
	private int id;
	private String account;
	private String groupId;
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
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	
}
