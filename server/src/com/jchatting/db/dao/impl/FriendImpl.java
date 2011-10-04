/**
 * 
 */
package com.jchatting.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.jchatting.db.bean.Friend;
import com.jchatting.db.bean.User;
import com.jchatting.db.bean.UserUser;
import com.jchatting.db.dao.FriendDao;
import com.jchatting.db.persistent.DbPoolUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午06:46:35
 */
public class FriendImpl implements FriendDao {

	/**
	 * 
	 */
	public FriendImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.FriendDao#getAllFriendByAccount(java.lang.String)
	 */
	@Override
	public Vector<Friend> getAllFriendListByAccount(String account) {
		// TODO Auto-generated method stub
		Vector<Friend> friends = new Vector<Friend>();
		String sql = "select users.id, users.account, password, sex, users.name, email, validated, online, forbidden, regtime, users.info, users.memo, attach from users join user_user on user_user.account_friend = users.account where user_user.account_user = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			
			PreparedStatement preparedStatement;
		
			preparedStatement = connection.prepareStatement(sql);
		
			preparedStatement.setString(1, account);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Friend friend = new Friend();
				friend.setId(resultSet.getInt(UserUser.ID));
				friend.setAccount(resultSet.getString(User.ACCOUNT));
				friend.setPassword(resultSet.getString(User.PASSWORD));
				friend.setSex(resultSet.getString(User.SEX));
				friend.setName(resultSet.getString(User.NAME));
				friend.setEmail(resultSet.getString(User.EMAIL));
				friend.setValidated("1".equals(resultSet.getString(User.VALIDATED)) ? true : false);
				friend.setOnline("1".equals(resultSet.getString(User.ONLINE)) ? true : false);
				friend.setForbidden("1".equals(resultSet.getString(User.FORBIDDEN)) ? true : false);		
				friend.setRegtime(resultSet.getTimestamp(User.REGTIME));
				friend.setRegtime(new Timestamp(System.currentTimeMillis()));
				friend.setInfo(resultSet.getString(User.INFO));
				friend.setUserMemo(resultSet.getString(UserUser.MEMO));
				friend.setAttach(resultSet.getString(UserUser.ATTACH));
				
				friends.add(friend);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(connection);
		}
		return friends;
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

	@Override
	public Map<String, Friend> getAllFriendMapByAccount(String account) {
		// TODO Auto-generated method stub
		Map<String, Friend> friends = new HashMap<String,Friend>();
		String sql = "select users.id, users.account, password, sex, users.name, email, validated, online, forbidden, regtime, users.info, users.memo, attach from users join user_user on user_user.account_friend = users.account where user_user.account_user = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			
			PreparedStatement preparedStatement;
		
			preparedStatement = connection.prepareStatement(sql);
		
			preparedStatement.setString(1, account);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Friend friend = new Friend();
				friend.setId(resultSet.getInt(UserUser.ID));
				friend.setAccount(resultSet.getString(User.ACCOUNT));
				friend.setPassword(resultSet.getString(User.PASSWORD));
				friend.setSex(resultSet.getString(User.SEX));
				friend.setName(resultSet.getString(User.NAME));
				friend.setEmail(resultSet.getString(User.EMAIL));
				friend.setValidated("1".equals(resultSet.getString(User.VALIDATED)) ? true : false);
				friend.setOnline("1".equals(resultSet.getString(User.ONLINE)) ? true : false);
				friend.setForbidden("1".equals(resultSet.getString(User.FORBIDDEN)) ? true : false);			
				friend.setRegtime(resultSet.getTimestamp(User.REGTIME));
				friend.setRegtime(new Timestamp(System.currentTimeMillis()));
				friend.setInfo(resultSet.getString(User.INFO));
				friend.setUserMemo(resultSet.getString(UserUser.MEMO));
				friend.setAttach(resultSet.getString(UserUser.ATTACH));
				
				friends.put(friend.getAccount(), friend);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeAll(connection);
		}
		return friends;
	}
}
