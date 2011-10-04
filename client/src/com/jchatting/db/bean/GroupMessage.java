/**
 * 
 */
package com.jchatting.db.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-23 下午07:27:34
 */
public class GroupMessage implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "group_message";
	
	public static final String ID = "id";
	public static final String SEND_ACCOUNT = "sent_account";
	public static final String RECEIVE_ID = "receive_id";
	public static final String CONTENT = "content";
	public static final String SENDTIME = "sendtime";
	public static final String MEMO = "memo";
	
	private int id;
	private int sendAccount;
	private int receiveId;
	private String content;
	private Timestamp sendTime;
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
	public int getSendAccount() {
		return sendAccount;
	}
	/**
	 * @param sendAccount the sendAccount to set
	 */
	public void setSendAccount(int sendAccount) {
		this.sendAccount = sendAccount;
	}
	/**
	 * @return the receiveId
	 */
	public int getReceiveId() {
		return receiveId;
	}
	/**
	 * @param receiveId the receiveId to set
	 */
	public void setReceiveId(int receiveId) {
		this.receiveId = receiveId;
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
