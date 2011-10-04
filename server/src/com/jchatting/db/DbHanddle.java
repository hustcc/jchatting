/**
 * 
 */
package com.jchatting.db;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import com.jchatting.db.bean.Friend;
import com.jchatting.db.bean.Group;
import com.jchatting.db.bean.User;
import com.jchatting.db.bean.UserInGroup;
import com.jchatting.db.bean.UserMessage;
import com.jchatting.db.bean.UserUser;
import com.jchatting.db.dao.impl.FriendImpl;
import com.jchatting.db.dao.impl.GroupImpl;
import com.jchatting.db.dao.impl.UserImpl;
import com.jchatting.db.dao.impl.UserInGroupImpl;
import com.jchatting.db.dao.impl.UserMessageImpl;
import com.jchatting.db.dao.impl.UserUserImpl;
import com.jchatting.pack.DataPackage;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-24 下午07:07:31
 */
public class DbHanddle {
	/**
	 * 退出群
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午09:30:29
	 * @param user
	 * @param userInGroup
	 * @return
	 */
	public boolean quitOutGroup(User user, UserInGroup userInGroup) {
		UserInGroupImpl userInGroupImpl = new UserInGroupImpl();
		if (userInGroupImpl.quitGroup(user, userInGroup)) {
			if (userInGroupImpl.getGroupUserCount(userInGroup.getId()) == 0) {
				new GroupImpl().delete(userInGroup.getId());
			}
			return true;
		}
		return false;
	}
	/**
	 * 创建群，返回群的id
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午09:17:00
	 * @param group
	 * @return
	 */
	public int createGroup(Group group) {
		return new GroupImpl().insert(group);
	}
	/**
	 * 通过群号得到群的所有信息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午09:09:56
	 * @param groupId
	 * @return
	 */
	public Group getGroupById(String groupId) {
		return new GroupImpl().findById(groupId);
	}
	/**
	 * 处理用户加群请求
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午08:56:54
	 * @param user
	 * @param groupId
	 * @return
	 */
	public int addUserIntoGroup(User user, String groupId) {
		return new UserInGroupImpl().addUserIntoGroup(user, groupId);
	}
	/**
	 * 获得一个群中的所有用户的id
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午08:47:54
	 * @param groupId
	 * @return
	 */
	public ArrayList<String> getAllUserAccountInGroup(String groupId) {
		return new UserInGroupImpl().findAllUserInGroup(groupId);
	}
	/**
	 * 获得一个用户的所有群信息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午04:37:41
	 * @param user
	 * @return
	 */
	public Map<String,UserInGroup> getAllGroupOfUser(User user) {
		return new UserInGroupImpl().findAllGroupOfUser(user);
	}
	/**
	 * 通过用户帐号获得用户的所有信息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午02:32:50
	 * @param account
	 * @return
	 */
	public User getUserByAccount(String account) {
		return new UserImpl().findByAccount(account);
	}
	/**
	 * 注册新用户
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午02:33:09
	 * @param user
	 * @return
	 */
	public int registeUser(User user) {
		return new UserImpl().insert(user);
	}
	/**
	 * add friend
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午01:32:09
	 * @param userAccount
	 * @param friendAccount
	 * @return
	 */
	public int addFriend(String userAccount, String friendAccount) {
		
		if (getUserByAccount(friendAccount) != null) {
			UserUser userUser = new UserUser();
			userUser.setAccountUser(userAccount);
			userUser.setAccountFriend(friendAccount);
			userUser.setAttach("");
			return new UserUserImpl().add(userUser);
		}
		else {
			return UserImpl.USER_NOT_EXIST;
		}
	}
	/**
	 * del friend
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午01:32:15
	 * @param userAccount
	 * @param friendAccount
	 * @return
	 */
	public boolean delFriend(String userAccount, String friendAccount) {
		UserUser userUser = new UserUser();
		userUser.setAccountUser(userAccount);
		userUser.setAccountFriend(friendAccount);
		return new UserUserImpl().delete(userUser);
	}
	
	public int setUserOnline(String account, boolean online) {
		return new UserImpl().setOnline(account, online);
	}
	/**
	 * 删除一个帐号对另一个帐号的离线消息
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-29 下午08:00:06
	 * @param sendAccount
	 * @param receiveAccount
	 * @return
	 */
	public boolean delMsgByAccount(String sendAccount, String receiveAccount) {
		return new UserMessageImpl().delMsgByAccount(sendAccount,
				receiveAccount);
	}
	/**
	 * 获得某一个帐号对一个帐号的离线消息
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-29 下午07:59:17
	 * @param sendAccount
	 * @param receiveAccount
	 * @return
	 */
	public Vector<UserMessage> getMsgByAccount(String sendAccount,
			String receiveAccount) {
		return new UserMessageImpl().findAllMsgByAccount(sendAccount,
				receiveAccount);
	}
	/**
	 * 保存用户离线消息
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-29 下午04:43:59
	 * @param dataPackage
	 * @return
	 * @throws SQLException
	 */
	public boolean insertOfflineMsg(DataPackage dataPackage) {
		UserMessage message = new UserMessage();
		message.setSendAccount(dataPackage.getSendId());
		message.setReceiveAccount(dataPackage.getReceiveId());
		message.setContent(dataPackage.getContent());
		message.setMemo("");
		message.setSendTime(new Timestamp(System.currentTimeMillis()));
		message.setRead(false);

		return new UserMessageImpl().insertMessage(message);
	}

	/**
	 * 获得某一个账户的所有好友
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-26 下午01:47:39
	 * @param account
	 * @return
	 */
	public Vector<Friend> getFriendList(String account) {
		return new FriendImpl().getAllFriendListByAccount(account);
	}
	/**
	 * 获得某一个账户的所有好友
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-30 下午07:33:23
	 * @param account
	 * @return
	 */
	public Map<String, Friend> getFriendMap(String account) {
		return new FriendImpl().getAllFriendMapByAccount(account);
	}

	// 用户账号被禁用
	public static final int USER_FORBIDDEN = -1;
	// 用户还没有通过审核
	public static final int USER_NOT_VALIDATED = -2;
	// 用户在线
	public static final int USER_ONLINE = 0;
	// 登录成功
	public static final int USER_LOGIN_SUCCESS = 1;
	// 登录失败，帐号密码不匹配
	public static final int USER_LOGIN_FAIL = -3;
	// 读数据库出错
	public static final int USER_LOGIN_ERROR = -4;
//	/**
//	 * 
//	 * 用户登录，根据不同的情况返回不同的值
//	 * 
//	 * @author Xewee.Zhiwei.Wang
//	 * @version 2011-9-25 下午04:04:00
//	 * @param account
//	 * @param password
//	 * @return
//	 */
//	public LoginResult login(String account, String password) {
//		LoginResult result = new LoginResult();
//		User user = new UserImpl().findByAccount(account);
//		result.setUser(user);
//		System.out.println(user.getAccount());
//		System.out.println(user.getPassword());
//		if (user == null) {
//			result.setReturnValue(USER_LOGIN_FAIL);
//			return result;
//		}
//
//		else {
//			if (user.getAccount().equals(account)
//					&& user.getPassword().equals(password)) {
//				if (user.isForbidden()) {
//					result.setReturnValue(USER_FORBIDDEN);
//					return result;
//				}
//				else if (!user.isValidated()) {
//					result.setReturnValue(USER_NOT_VALIDATED);
//					return result;
//				}
//				else if (user.isOnline()) {
//					result.setReturnValue(USER_ONLINE);
//					return result;
//				}
//				result.setReturnValue(USER_LOGIN_SUCCESS);
//				return result;
//			}
//			result.setReturnValue(USER_LOGIN_FAIL);
//			return result;
//		}
//	}
}
