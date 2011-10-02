/**
 * 
 */
package com.jchatting.db.bean;

import java.sql.Timestamp;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-2 下午03:11:59
 */
public class UserInGroup {

	private String account;
	private String attach;
	
	private String id;
	private String name;
	private Timestamp createtime;
	private String info;
	private String memo;
	/**
	 * 
	 */
	public UserInGroup() {
		// TODO Auto-generated constructor stub
	}
	public UserInGroup(Group group, String account, String attach) {
		// TODO Auto-generated constructor stub
		this.account = account;
		this.attach = attach;
		this.id = String.valueOf(group.getId());
		this.name = group.getName();
		this.createtime = group.getCreatetime();
		this.info = group.getInfo();
		this.memo = group.getMemo();
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name + "  [ID: " +
				id + "]  ";
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
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the createtime
	 */
	public Timestamp getCreatetime() {
		return createtime;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
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

}
