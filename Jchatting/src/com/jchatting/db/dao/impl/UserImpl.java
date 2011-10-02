/**
 * 
 */
package com.jchatting.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.jchatting.db.bean.User;
import com.jchatting.db.dao.UserDao;
import com.jchatting.db.persistent.DbPoolUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-24 下午07:15:51
 */
public class UserImpl implements UserDao {

	public static final int USER_EXIST = -101;
	public static final int USER_NOT_EXIST = -102;
	/**
	 * 
	 */
	public UserImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserDao#countAll()
	 */
	@Override
	public int countAll() throws SQLException {
		// TODO Auto-generated method stub
		final String count_num_name = "row_num";
		
		String sql = "select COUNT("+ User.ACCOUNT +") AS " + count_num_name + " from " + User.TABLE_NAME + ";";
		System.out.println(sql);
		Connection connection = DbPoolUtil.getInstance().getConnection();
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()) {
			return resultSet.getInt(count_num_name);
		}
		closeAll(connection);
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserDao#delete(java.lang.String)
	 */
	@Override
	public int delete(String Account) throws SQLException {
		// TODO Auto-generated method stub
		Connection connection = DbPoolUtil.getInstance().getConnection();
		String sql = "delete from " + User.TABLE_NAME + " where " + User.ACCOUNT + "=" + "(?);";
		System.out.println(sql);
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, Account);
		
		int count = preparedStatement.executeUpdate();
		closeAll(connection);
		return count;
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserDao#findByAccount(java.lang.String)
	 */
	@Override
	public User findByAccount(String account) {
		// TODO Auto-generated method stub
		
		String sql = "select * from " + User.TABLE_NAME + " where " + User.ACCOUNT + "=(?)";
		System.out.println(sql);
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			User user = new User();
			while (resultSet.next()) {
				user.setAccount(resultSet.getString(User.ACCOUNT));
				user.setEmail(resultSet.getString(User.EMAIL));
				user.setForbidden(resultSet.getInt(User.FORBIDDEN) == 1 ? true : false);
				user.setId(resultSet.getInt(User.ID));
				user.setInfo(resultSet.getString(User.INFO));
				user.setMemo(resultSet.getString(User.MEMO));
				user.setName(resultSet.getString(User.NAME));
				user.setOnline(resultSet.getInt(User.ONLINE) == 1 ? true : false);
				user.setPassword(resultSet.getString(User.PASSWORD));
				user.setRegtime(resultSet.getTimestamp(User.REGTIME));
				user.setSex(resultSet.getString(User.SEX));
				user.setValidated(resultSet.getInt(User.VALIDATED) == 1 ? true : false);
				
				return user;
			}
		} catch (SQLException e) {
			return null;
		} finally {
			closeAll(connection);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserDao#insert(com.jchatting.db.bean.User)
	 */
	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		String sql_query = "select account from users where account = (?);";
		String sql_insert = "insert into users(account, password, sex, name, email, validated, online, forbidden, regtime, info, memo) values (?,?,?,?,?,?,?,?,?,?,?)";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
			preparedStatement.setString(1, user.getAccount());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return USER_EXIST;
			}
			else {
				preparedStatement = connection.prepareStatement(sql_insert);
				preparedStatement.setString(1, user.getAccount());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getSex());
				preparedStatement.setString(4, user.getName());
				preparedStatement.setString(5, user.getEmail());
				preparedStatement.setString(6, user.isValidated() ? "1" : "0");
				preparedStatement.setString(7, user.isOnline() ? "1" : "0");
				preparedStatement.setString(8, user.isForbidden() ? "1" : "0");
				preparedStatement.setTimestamp(9, user.getRegtime());
				preparedStatement.setString(10, user.getInfo());
				preparedStatement.setString(11, user.getMemo());
				
				return preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		} finally {
			closeAll(connection);
		}
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserDao#selectAll()
	 */
	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserDao#update(com.jchatting.db.bean.User)
	 */
	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return 0;
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
	public int setOnline(String account, boolean online) {
		// TODO Auto-generated method stub
		String sql = "update users set online = (?) where account = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, online ? "1" : "0");
			preparedStatement.setString(2, account);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return 0;
		} finally {
			closeAll(connection);
		}
	}

}
