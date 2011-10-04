/**
 * 
 */
package com.jchatting.db.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 下午3:10:35
 *
 */
public class UserMessage implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "user_message";
	
	public static final String ID = "id";
	public static final String SEND_ACCOUNT = "send_account";
	public static final String RECEIVE_ACCOUNT = "receive_account";
	public static final String CONTENT = "content";
	public static final String SENDTIME = "sendtime";
	public static final String READ = "read";
	public static final String MEMO = "memo";
	
	private int id;
	private String sendAccount;
	private String receiveAccount;
	private String content;
	private Timestamp sendTime;
	private boolean read;
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
	 * @return the sendAccount
	 */
	public String getSendAccount() {
		return sendAccount;
	}
	/**
	 * @param sendAccount the sendAccount to set
	 */
	public void setSendAccount(String sendAccount) {
		this.sendAccount = sendAccount;
	}
	/**
	 * @return the receiveAccount
	 */
	public String getReceiveAccount() {
		return receiveAccount;
	}
	/**
	 * @param receiveAccount the receiveAccount to set
	 */
	public void setReceiveAccount(String receiveAccount) {
		this.receiveAccount = receiveAccount;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the sendTime
	 */
	public Timestamp getSendTime() {
		return sendTime;
	}
	/**
	 * @param sendTime the sendTime to set
	 */
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * @return the read
	 */
	public boolean isRead() {
		return read;
	}
	/**
	 * @param read the read to set
	 */
	public void setRead(boolean read) {
		this.read = read;
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
