/**
 * 
 */
package com.jchatting.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jchatting.db.bean.User;
import com.jchatting.db.bean.UserInGroup;
import com.jchatting.db.dao.UserInGroupDao;
import com.jchatting.db.persistent.DbPoolUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-2 下午03:11:30
 */
public class UserInGroupImpl implements UserInGroupDao {

	public final static int GROUP_NOT_EXIST = -104;
	public final static int GROUP_EXIST = -105;
	public final static int USER_IN_GROUP = -106;
	public final static int ADD_USER_TO_GROUP_SUCCESS = 1;
	public final static int ADD_USER_TO_GROUP_ERROR = -1;
	/**
	 * 
	 */
	public UserInGroupImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 用户退群
	 *TODO当一个群的人数为0时，删除群
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午09:34:54
	 * @param user
	 * @param userInGroup
	 * @return
	 */
	public boolean quitGroup(User user, UserInGroup userInGroup) {
		String sql = "delete from user_group where account = (?) and group_id = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getAccount());
			preparedStatement.setString(2, userInGroup.getId());
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		} finally {
			closeAll(connection);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.jchatting.db.dao.UserInGroupDao#findAllGroupOfUser(com.jchatting.db.bean.User)
	 */
	@Override
	public Map<String, UserInGroup> findAllGroupOfUser(User user) {
		// TODO Auto-generated method stub
		
		Map<String, UserInGroup> userGroupMap = new HashMap<String, UserInGroup>();
		String sql = "SELECT user_group.account, user_group.attach, groups.id, groups.name, groups.createtime, groups.info, user_group.memo FROM user_group INNER JOIN groups ON user_group.group_id = groups.id WHERE user_group.account = (?)";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getAccount());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println("result while");
				
				UserInGroup userInGroup = new UserInGroup();
				userInGroup.setId(resultSet.getString("id"));
				userInGroup.setAccount(resultSet.getString("account"));
				userInGroup.setAttach(resultSet.getString("attach"));
				userInGroup.setCreatetime(resultSet.getTimestamp("createtime"));
				userInGroup.setInfo(resultSet.getString("info"));
				userInGroup.setMemo(resultSet.getString("memo"));
				userInGroup.setName(resultSet.getString("name"));
				
				userGroupMap.put(String.valueOf(userInGroup.getId()), userInGroup);
			}
			
			return userGroupMap;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new HashMap<String, UserInGroup>();
		} finally {
			closeAll(connection);
		}
	}
	/**
	 * 得到某一个群里面的所有用户account
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午04:45:12
	 * @param groupId
	 * @return
	 */
	public ArrayList<String> findAllUserInGroup(String groupId) {
		ArrayList<String> userAccount = new ArrayList<String>();
		
		String sql = "select account from user_group where group_id = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, groupId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userAccount.add(resultSet.getString("account"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		} finally {
			closeAll(connection);
		}
		
		return userAccount;
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
	public int addUserIntoGroup(User user, String groupId) {
		// TODO Auto-generated method stub
		String sql_query1 = "select id from groups where id = (?);";
		String sql_query2 = "select id from user_group where account = (?) and group_id = (?);";
		String sql_insert = "insert into user_group(account, group_id, attach, memo) values (?, ?, ?, ?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql_query1);
			preparedStatement.setString(1, groupId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (! resultSet.next()) {
				return GROUP_NOT_EXIST;
			}
			
			preparedStatement = connection.prepareStatement(sql_query2);
			preparedStatement.setString(1, user.getAccount());
			preparedStatement.setString(2, groupId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return USER_IN_GROUP;
			}
			
			preparedStatement = connection.prepareStatement(sql_insert);
			preparedStatement.setString(1, user.getAccount());
			preparedStatement.setString(2, groupId);
			preparedStatement.setString(3, "");
			preparedStatement.setString(4, "");
			
			preparedStatement.execute();
			return ADD_USER_TO_GROUP_SUCCESS;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return ADD_USER_TO_GROUP_ERROR;
		} finally {
			closeAll(connection);
		}
	}

	@Override
	public int getGroupUserCount(String groupId) {
		String sql = "select COUNT(id) as num from user_group where group_id = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, groupId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				return resultSet.getInt("num");
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return -1;
		} finally {
			closeAll(connection);
		}
	}

}
