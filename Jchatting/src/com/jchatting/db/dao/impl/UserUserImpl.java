/**
 * 
 */
package com.jchatting.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.jchatting.db.bean.UserUser;
import com.jchatting.db.dao.UserUserDao;
import com.jchatting.db.persistent.DbPoolUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-25 下午04:08:45
 */
public class UserUserImpl implements UserUserDao {

	/**
	 * 
	 */
	public UserUserImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserUserDao#findAllByAccount(java.lang.String)
	 */
	@Override
	public Vector<UserUser> findAllByAccount(String account) {
		// TODO Auto-generated method stub
		Vector<UserUser> userVector = new Vector<UserUser>();
		
		String sql = "select " 
						+ UserUser.ACCOUNT_FRIEND + "," 
						+ UserUser.ATTACH + ","
						+ UserUser.MEMO + ","
						+ UserUser.ID + " from "
						+ UserUser.TABLE_NAME + " where "
						+ UserUser.ACCOUNT_USER + "=(?);";
		Connection connection = null;
		try {
			connection = DbPoolUtil.getInstance().getConnection();
			PreparedStatement preparedStatement;
			
				preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, account);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				UserUser user = new UserUser();
				user.setId(resultSet.getInt(UserUser.ID));
				user.setAccountUser(account);
				user.setAccountFriend(resultSet.getString(UserUser.ACCOUNT_FRIEND));
				user.setAttach(resultSet.getString(UserUser.ATTACH));
				user.setMemo(resultSet.getString(UserUser.MEMO));
				
				userVector.add(user);
			}
		} catch (SQLException e) {
			return null;
		} finally {
			closeAll(connection);
		}
		return userVector;
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
