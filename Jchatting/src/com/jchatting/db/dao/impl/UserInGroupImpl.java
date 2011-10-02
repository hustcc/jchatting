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

	/**
	 * 
	 */
	public UserInGroupImpl() {
		// TODO Auto-generated constructor stub
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

}
