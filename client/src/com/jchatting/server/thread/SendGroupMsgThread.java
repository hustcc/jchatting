/**
 * 
 */
package com.jchatting.server.thread;

import java.io.IOException;
import java.util.ArrayList;

import com.jchatting.db.DbHanddle;
import com.jchatting.pack.DataPackage;
import com.jchatting.server.util.ClientPool;
import com.jchatting.server.util.ServerMsgUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-2 下午04:33:24
 */
public class SendGroupMsgThread extends Thread {

	private DataPackage dataPackage;
	private String groupId;
	
	public SendGroupMsgThread(DataPackage dataPackage) {
		this.dataPackage = dataPackage;
		this.groupId = this.dataPackage.getReceiveId();
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		System.out.println("forward group message!");
		ArrayList<String> userAccountInGroup = new DbHanddle().getAllUserAccountInGroup(this.groupId);
		
		for (int i = 0; i < userAccountInGroup.size(); i++) {
			String userId = userAccountInGroup.get(i);
			if (ClientPool.getClient(userId) != null && !dataPackage.getSendId().equals(userId)) {
				System.out.println("forward group msg to:" + userId);
				try {
					ServerMsgUtil.sendGroupMsg(dataPackage, userId);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("SendGroupMsgThread.run() IOException!");
					return;
				}
			}
		}
	}
	
	
}
