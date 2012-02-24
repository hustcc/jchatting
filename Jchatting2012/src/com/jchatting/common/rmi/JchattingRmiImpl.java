/**
 * 使用java rmi技术调用的接口实现类
 */
package com.jchatting.common.rmi;

import java.util.List;

import com.jchatting.common.exception.IllegalUserException;
import com.jchatting.common.rmi.bean.Friend;
import com.jchatting.common.rmi.bean.Group;
import com.jchatting.common.rmi.bean.Message;
import com.jchatting.common.rmi.bean.User;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2012-2-24 下午3:13:17
 */
public class JchattingRmiImpl implements JchattingRmi {

	/* (non-Javadoc)
	 * @see com.jchatting.common.rmi.JchattingRmiInterface#getFriendList(com.jchatting.common.rmi.bean.LoginUser)
	 */
	@Override
	public List<Friend> getFriendList(User user) throws IllegalUserException {
		if (verifyUser(user)) {
			// TODO 查询数据库，获得数据
		}
		throw new IllegalUserException("User and password not match.");
	}
	@Override
	public boolean addFriend(User user, Friend friend)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateFriend(User user, Friend friend)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteFriend(User user, Friend friend)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteFriendList(User user, List<Friend> friendList)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.jchatting.common.rmi.JchattingRmiInterface#getGroupList(com.jchatting.common.rmi.bean.LoginUser)
	 */
	@Override
	public List<Group> getGroupList(User user) throws IllegalUserException {
		if (verifyUser(user)) {
			// TODO 查询数据库，获得数据
		}
		throw new IllegalUserException("User and password not match.");

	}
	@Override
	public boolean addGroup(User user, Group group) throws IllegalUserException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateGroup(User user, Group group)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean quitGroup(User user, Group group)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see com.jchatting.common.rmi.JchattingRmi#getOfflineUserMsg(com.jchatting.common.rmi.bean.User)
	 */
	@Override
	public List<Message> getOfflineUserMsg(User user)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.jchatting.common.rmi.JchattingRmi#getOfflineGroupMsg(com.jchatting.common.rmi.bean.User)
	 */
	@Override
	public List<Message> getOfflineGroupMsg(User user)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.jchatting.common.rmi.JchattingRmi#getOfflineMsg(com.jchatting.common.rmi.bean.User)
	 */
	@Override
	public List<Message> getOfflineMsg(User user) throws IllegalUserException {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see com.jchatting.common.rmi.JchattingRmi#sendOfflineMsg(com.jchatting.common.rmi.bean.User, com.jchatting.common.rmi.bean.Message)
	 */
	@Override
	public boolean sendOfflineMsg(User user, Message message)
			throws IllegalUserException {
		// TODO Auto-generated method stub
		return false;
	}
	private boolean verifyUser(User user) {
		// TODO 查询数据库，验证用户
		return true;
	}
}
