/**
 * 
 */
package com.jchatting.db.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午06:42:28
 */
public class Friend implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String account;
	private String password;
	private String sex;
	private String name;
	private String email;
	private boolean validated;
	private boolean online;
	private boolean forbidden;
	private Timestamp regtime;
	private String info;
	private String userMemo;
	
	private String attach;
	private String userUserMemo;
	
	private String offLineMsgNum;
	

	/**
	 * 
	 */
	public Friend() {
		// TODO Auto-generated constructor stub
	}
	public Friend(User user, String attach, String userUserMemo, String offLineMsgNum) {
		this.id = user.getId();
		this.account = user.getAccount();
		this.password = user.getPassword();
		this.sex = user.getSex();
		this.name = user.getName();
		this.email = user.getEmail();
		this.validated = user.isValidated();
		this.online = user.isOnline();
		this.forbidden = user.isForbidden();
		this.regtime = user.getRegtime();
		this.info = user.getInfo();
		this.userMemo = user.getMemo();
		
		this.userUserMemo = userUserMemo;
		this.offLineMsgNum = offLineMsgNum;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.account + 
				"  ["+ this.attach + "] " +
				(this.offLineMsgNum == null || this.offLineMsgNum.equals("0") ? "" :
				"  [" + this.offLineMsgNum + "]");
	}


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


	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}


	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the validated
	 */
	public boolean isValidated() {
		return validated;
	}


	/**
	 * @param validated the validated to set
	 */
	public void setValidated(boolean validated) {
		this.validated = validated;
	}


	/**
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}


	/**
	 * @param online the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}


	/**
	 * @return the forbidden
	 */
	public boolean isForbidden() {
		return forbidden;
	}


	/**
	 * @param forbidden the forbidden to set
	 */
	public void setForbidden(boolean forbidden) {
		this.forbidden = forbidden;
	}


	/**
	 * @return the regtime
	 */
	public Timestamp getRegtime() {
		return regtime;
	}


	/**
	 * @param regtime the regtime to set
	 */
	public void setRegtime(Timestamp regtime) {
		this.regtime = regtime;
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
	 * @return the userMemo
	 */
	public String getUserMemo() {
		return userMemo;
	}


	/**
	 * @param userMemo the userMemo to set
	 */
	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
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
	 * @return the userUserMemo
	 */
	public String getUserUserMemo() {
		return userUserMemo;
	}


	/**
	 * @param userUserMemo the userUserMemo to set
	 */
	public void setUserUserMemo(String userUserMemo) {
		this.userUserMemo = userUserMemo;
	}


	/**
	 * @return the offLineMsgNum
	 */
	public String getOffLineMsgNum() {
		return offLineMsgNum;
	}


	/**
	 * @param offLineMsgNum the offLineMsgNum to set
	 */
	public void setOffLineMsgNum(String offLineMsgNum) {
		this.offLineMsgNum = offLineMsgNum;
	}
	
	

	
}
