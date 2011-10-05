/**
 * 
 */
package com.jchatting.pack;

import java.io.Serializable;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-23 下午08:51:05
 */
public class DataPackage implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 由服务器发送给客户端的配置信息
	 */
	public static final int CONFIG = 0;
	/**
	 * 好友之间的消息
	 */
	public static final int USER = 1;
	/**
	 * 群消息
	 */
	public static final int GROUP = 2;
	/**
	 * 由系统发送的广告信息
	 */
	public static final int SYSTEM = 3;
	/**
	 * 用户上线提醒信息
	 */
	public static final int CLIENT_ON = 4;
	/**
	 * 用户下线是提醒 
	 */
	public static final int CLIENT_OFF = 5;
	/**
	 * 发送文件请求
	 */
	public static final int FILE_TRANS = 6;
	private int type;
	private String sendId;
	private String receiveId;
	private String content;
	
	/**
	 * 
	 */
	public DataPackage() {
		this(-1, "", "", "");
	}
	
	public DataPackage(int type, String sendId, String receiveId, String content) {
		this.type = type;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.content = content;
	}
	

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the sendId
	 */
	public String getSendId() {
		return sendId;
	}

	/**
	 * @param sendId the sendId to set
	 */
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	/**
	 * @return the receiveId
	 */
	public String getReceiveId() {
		return receiveId;
	}

	/**
	 * @param receiveId the receiveId to set
	 */
	public void setReceiveId(String receiveId) {
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
	
}
