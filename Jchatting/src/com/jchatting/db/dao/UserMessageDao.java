/**
 * 
 */
package com.jchatting.db.dao;

import java.util.Vector;

import com.jchatting.db.bean.UserMessage;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-29 下午04:41:10
 */
public interface UserMessageDao {
	public boolean insertMessage(UserMessage message);
	public Vector<UserMessage> findAllMsgByAccount(String sendAccount, String receiveAccount);
	public boolean delMsgByAccount(String sendAccount, String receiveAccount);
}
