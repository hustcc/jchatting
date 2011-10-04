/**
 * 
 */
package com.jchatting.db.dao;

import java.sql.SQLException;
import java.util.List;

import com.jchatting.db.bean.User;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-24 下午07:07:59
 */
public interface UserDao {
	
	public int setOnline(String account, boolean online);
	
	public int insert(User user);
    
    public int update(User user);
    
    public int delete(String Account) throws SQLException;
    
    public List<User> selectAll();
    
    public int countAll() throws SQLException;
    
    public User findByAccount(String Account);
}
