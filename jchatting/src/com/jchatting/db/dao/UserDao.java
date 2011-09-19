/**
 * 
 */
package com.jchatting.db.dao;

import java.util.List;

import com.jchatting.db.bean.User;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-17 下午3:15:31
 *
 */
public interface UserDao {

	public int insert(User user);
    
    public int update(User user);
    
    public int delete(String Account);
    
    public List<User> selectAll();
    
    public int countAll();
    
    public User findByAccount(String Account);
}
