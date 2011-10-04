/**
 * 
 */
package com.jchatting.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.jchatting.db.bean.UserMessage;
import com.jchatting.db.dao.UserMessageDao;
import com.jchatting.db.persistent.DbPoolUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-29 下午04:42:35
 */
public class UserMessageImpl implements UserMessageDao {

	/**
	 * 
	 */
	public UserMessageImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserMessageDao#insertMessage(com.jchatting.db.bean.UserMessage)
	 */
	@Override
	public boolean insertMessage(UserMessage message) {
		// TODO Auto-generated method stub
		String sql = "insert into user_message(send_account, receive_account, content, sendtime, isread, memo) values (?, ?, ?, ?, ?, ?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, message.getSendAccount());
			preparedStatement.setString(2, message.getReceiveAccount());
			preparedStatement.setString(3, message.getContent());
			preparedStatement.setTimestamp(4, message.getSendTime());
			preparedStatement.setString(5, message.isRead() ? "1" : "0");
			preparedStatement.setString(6, message.getMemo());
			return preparedStatement.executeUpdate() == 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		} finally {
			closeAll(connection);
		}
	}
	

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserMessageDao#findAllMsgByAccount(java.lang.String, java.lang.String)
	 */
	@Override
	public Vector<UserMessage> findAllMsgByAccount(String sendAccount,
			String receiveAccount) {
		Vector<UserMessage> messages = new Vector<UserMessage>();
		String sql = "select id, send_account, receive_account, content, sendtime, isread, memo " +
						"from user_message where " +
						"send_account = (?) and receive_account = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sendAccount);
			preparedStatement.setString(2, receiveAccount);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserMessage message = new UserMessage();
				message.setId(resultSet.getInt("id"));
				message.setReceiveAccount(resultSet.getString("receive_account"));
				message.setSendAccount(resultSet.getString("send_account"));
				message.setContent(resultSet.getString("content"));
				message.setSendTime(resultSet.getTimestamp("sendtime"));
				message.setRead("1".equalsIgnoreCase(resultSet.getString("isread")));
				message.setMemo(resultSet.getString("memo"));
				
				messages.add(message);
			}
		} catch (SQLException e) {
			messages = new Vector<UserMessage>();
		} finally {
			closeAll(connection);
		}
		
		return messages;
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserMessageDao#delMsgByAccount(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean delMsgByAccount(String sendAccount, String receiveAccount) {
		String sql = "delete " +
						"from user_message where " +
						"send_account = (?) and receive_account = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sendAccount);
			preparedStatement.setString(2, receiveAccount);
			return preparedStatement.execute();
		} catch (SQLException e) {
			return false;
		} finally {
			closeAll(connection);
		}
	}

	private void closeAll(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
