/**
 * 
 */
package com.jchatting.client.thread;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import javax.swing.JOptionPane;

import com.jchatting.client.config.FileSocketConfig;
import com.jchatting.client.ui.ChatGroupFrame;
import com.jchatting.client.ui.ChatMainFrame;
import com.jchatting.client.ui.ChatUserFrame;
import com.jchatting.client.ui.LoginFrame;
import com.jchatting.client.util.ChatGroupFramePool;
import com.jchatting.client.util.ChatUserFramePool;
import com.jchatting.client.util.ClientMsgUtil;
import com.jchatting.db.DbHanddle;
import com.jchatting.db.bean.Friend;
import com.jchatting.db.bean.UserInGroup;
import com.jchatting.pack.DataPackage;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午09:34:14
 */
public class ClientThread extends Thread {

//	private DataInputStream reader;
//	private DataOutputStream writer;
	
	private ChatMainFrame mainFrame;
	
	private DataPackage dataPackage = null;
	public ClientThread(ChatMainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("client thread started");
		try {
			while(true) {
				dataPackage = ClientMsgUtil.receiveMsg(ChatMainFrame.getSocket());
				forward(dataPackage);
			}	
		} catch (IOException e) {
			socketException();
		}	
	}
	/**
	 * 两个相同的帐号登录，先登录的被挤下线
	 * 或者
	 * 网络存在异常，服务器关闭，帐号退出
	 * 重新登录
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-29 下午01:50:17
	 */
	private void socketException() {
		try {
			new DbHanddle().setUserOnline(mainFrame.getUser().getAccount(), false);
			ChatUserFramePool.disposeAllFrame();
			ChatMainFrame.getSocket().close();
			ChatMainFrame.setSocket(null);
			JOptionPane.showMessageDialog(mainFrame, "您的帐号在其他地方登录，或者网络存在异常！", "Error", JOptionPane.ERROR_MESSAGE);
			mainFrame.setVisible(false);
			mainFrame.dispose();
			mainFrame = null;
			new LoginFrame().setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 用户名为account的好友上线
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 上午11:25:48
	 * @param account
	 */
	private void friendOnOffline(String account, boolean online) {
		Map<String, Friend> friendMap = mainFrame.getFriendMap();
		Friend friend = friendMap.get(account);
		friend.setOnline(online);
		friendMap.put(account, friend);
		
		mainFrame.reConstractUserTree(friendMap);
		
	}
	/**
	 * 转发消息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-28 下午09:54:19
	 */
	private void forward(DataPackage dataPackage) {
		int type = dataPackage.getType();
		String sendId = dataPackage.getSendId();
		String receiveId = dataPackage.getReceiveId();
//		String content = dataPackage.getContent();
		ChatUserFrame chatUserFrame = null;
		ChatGroupFrame chatGroupFrame = null;
		switch (type) {
			
			case DataPackage.SYSTEM :
				//TODO
				break;
			case DataPackage.FILE_TRANS :
				FileSocketConfig fileSocketConfig = FileSocketConfig.valueOf(dataPackage.getContent());
				//如果从收到的数据包中可以解析出fileSocketConfig
				if (fileSocketConfig != null) {
					//将下载任务交给相应的chatuserframe处理，如果不存在，则新建
					chatUserFrame = ChatUserFramePool.getChatFrame(sendId);
					if (chatUserFrame == null) {
						chatUserFrame = new ChatUserFrame(mainFrame, mainFrame.getFriendByAccount(sendId));
						ChatUserFramePool.addChatFrame(chatUserFrame);
					}
					chatUserFrame.setVisible(true);
					chatUserFrame.receiveFileAction(fileSocketConfig);
				}
				
				break;
				
			//服务器转发好友上线消息
			case DataPackage.CLIENT_ON :
				//TODO
				System.out.println("friend " + sendId + " online!");
				friendOnOffline(sendId, true);
				break;
				
			case DataPackage.CLIENT_OFF :
				//TODO
				System.out.println("friend " + sendId + " offline!");
				friendOnOffline(sendId, false);
				break;
				
			case DataPackage.USER :
				chatUserFrame = ChatUserFramePool.getChatFrame(sendId);
				if (chatUserFrame != null) {
					chatUserFrame.setVisible(true);
					chatUserFrame.receiveMsg(dataPackage, new Timestamp(System.currentTimeMillis()));
				}
				else {
					//TODO 1.自动跳出聊天窗口
					Friend friend = mainFrame.getFriendByAccount(sendId);
					chatUserFrame = new ChatUserFrame(mainFrame, friend);
					chatUserFrame.setVisible(true);
					ChatUserFramePool.addChatFrame(chatUserFrame);
					chatUserFrame.receiveMsg(dataPackage, new Timestamp(System.currentTimeMillis()));
					
					//TODO 2.在主界面显示在消息数
					
				}
				break;
				
			case DataPackage.FILE_TRANS_CANCEL :
//				System.out.println("收到停止传送的消息，来自" + dataPackage.getSendId());
				chatUserFrame = ChatUserFramePool.getChatFrame(sendId);
				if (chatUserFrame != null) {
					chatUserFrame.setVisible(true);
					chatUserFrame.recvCancelRefuseFileTransMsg(dataPackage);
				}
				else {
					//TODO 1.自动跳出聊天窗口
					Friend friend = mainFrame.getFriendByAccount(sendId);
					chatUserFrame = new ChatUserFrame(mainFrame, friend);
					chatUserFrame.setVisible(true);
					ChatUserFramePool.addChatFrame(chatUserFrame);
					chatUserFrame.recvCancelRefuseFileTransMsg(dataPackage);
					
					
				}
				break;
			case DataPackage.GROUP :
				chatGroupFrame = ChatGroupFramePool.getChatFrame(receiveId);
				if (chatGroupFrame != null) {
					chatGroupFrame.setVisible(true);
					chatGroupFrame.receiveMsg(dataPackage, new Timestamp(System.currentTimeMillis()));
				}
				else {
					//TODO 1.自动跳出聊天窗口
					UserInGroup userInGroup = mainFrame.getUserInGroupByGroupId(receiveId);
					chatGroupFrame = new ChatGroupFrame(mainFrame, userInGroup);
					chatGroupFrame.setVisible(true);
					ChatGroupFramePool.addChatFrame(chatGroupFrame);
					chatGroupFrame.receiveMsg(dataPackage, new Timestamp(System.currentTimeMillis()));
					
					//TODO 2.在主界面显示在消息数
					
				}
				break;
			default :
				//TODO
				break;
		}
	}
}
