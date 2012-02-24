/**
 * 使用java rmi技术调用的接口类
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
 * @version 2012-2-24 下午3:06:13
 */
public interface JchattingRmi {
		
	public List<Friend> getFriendList(User user) throws IllegalUserException;
	public boolean addFriend(User user, Friend friend) throws IllegalUserException;
	public boolean updateFriend(User user, Friend friend) throws IllegalUserException;
	public boolean deleteFriend(User user, Friend friend) throws IllegalUserException;
	public boolean deleteFriendList(User user, List<Friend> friendList) throws IllegalUserException;
	public List<Message> getOfflineUserMsg(User user) throws IllegalUserException;
	
	public List<Group> getGroupList(User user) throws IllegalUserException;
	public boolean addGroup(User user, Group group) throws IllegalUserException;
	public boolean updateGroup(User user, Group group) throws IllegalUserException;
	public boolean quitGroup(User user, Group group) throws IllegalUserException;
	public List<Message> getOfflineGroupMsg(User user) throws IllegalUserException;
	
	public List<Message> getOfflineMsg(User user) throws IllegalUserException;
	
	public boolean sendOfflineMsg(User user, Message message) throws IllegalUserException;
}
