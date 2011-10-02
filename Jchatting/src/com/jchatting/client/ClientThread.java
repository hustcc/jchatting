/**
 * 
 */
package com.jchatting.client;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import javax.swing.JOptionPane;

import com.jchatting.client.ui.ChatMainFrame;
import com.jchatting.client.ui.ChatUserFrame;
import com.jchatting.client.ui.LoginFrame;
import com.jchatting.db.bean.Friend;
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
			twoClientOnline();
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
	private void twoClientOnline() {
		try {
			JOptionPane.showMessageDialog(mainFrame, "您的帐号在其他地方登录，或者网络存在异常！", "Error", JOptionPane.ERROR_MESSAGE);
			ChatFramePool.disposeAllFrame();
			ChatMainFrame.getSocket().close();
			ChatMainFrame.setSocket(null);
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
		
		mainFrame.reConstractTree(friendMap);
		
	}
	/**
	 * 转发消息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-28 下午09:54:19
	 */
	private void forward(DataPackage dataPackage) {
		int type = dataPackage.getType();
		String sendId = dataPackage.getSendId();
//		String receiveId = dataPackage.getReceiveId();
//		String content = dataPackage.getContent();
		switch (type) {
			case DataPackage.SYSTEM :
				//TODO
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
				ChatUserFrame chatUserFrame = ChatFramePool.getChatFrame(sendId);
				if (chatUserFrame != null) {
					chatUserFrame.setVisible(true);
					chatUserFrame.receiveMsg(dataPackage, new Timestamp(System.currentTimeMillis()));
				}
				else {
					//TODO 1.自动跳出聊天窗口
					Friend friend = mainFrame.getFriendByAccount(dataPackage.getSendId());
					chatUserFrame = new ChatUserFrame(mainFrame, friend);
					chatUserFrame.setVisible(true);
					ChatFramePool.addChatFrame(chatUserFrame);
					chatUserFrame.receiveMsg(dataPackage, new Timestamp(System.currentTimeMillis()));
					
					//TODO 2.在主界面显示在消息数
					
				}
				break;
				
			case DataPackage.GROUP :
				//TODO	
				break;
					
			case DataPackage.OTHER :
				//TODO		
				break;
			default :
				//TODO
				break;
		}
	}
}
