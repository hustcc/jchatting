package com.jchatting.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jchatting.db.bean.Group;
import com.jchatting.db.dao.GroupDao;
import com.jchatting.db.persistent.DbPoolUtil;

public class GroupImpl implements GroupDao {

	@Override
	public Group findById(String groupId) {
		// TODO Auto-generated method stub
		Group group = new Group(); 
		String sql = "select id, name, createtime, info, memo from groups where id = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, groupId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				group.setId(Integer.valueOf(resultSet.getString("id")));
				group.setName(resultSet.getString("name"));
				group.setCreatetime(resultSet.getTimestamp("createtime"));
				group.setInfo(resultSet.getString("info"));
				group.setMemo(resultSet.getString("memo"));
				
				return group;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		} finally {
			closeAll(connection);
		}
		return null;
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
	/**
	 * 创建群，返回群的id
	 */
	@Override
	public int insert(Group group) {
		// TODO Auto-generated method stub
		String sql = "insert into groups(name, createtime, info, memo) values (?, ?, ?, ?);";
		
		String sql_query = "select id from groups where createtime = (?) order by id desc;";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, group.getName());
			preparedStatement.setTimestamp(2, group.getCreatetime());
			preparedStatement.setString(3, group.getInfo());
			preparedStatement.setString(4, group.getMemo());
			if (preparedStatement.executeUpdate() > 0) {
				preparedStatement = connection.prepareStatement(sql_query);
				preparedStatement.setTimestamp(1, group.getCreatetime());
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					return resultSet.getInt("id");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return -1;
		} finally {
			closeAll(connection);
		}
		return -1;
	}
	@Override
	public boolean delete(String groupId) {
		// TODO Auto-generated method stub
		String sql = "delete from groups where id = (?);";
		Connection connection = DbPoolUtil.getInstance().getConnection();
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, groupId);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		} finally {
			closeAll(connection);
		}
	}

}
