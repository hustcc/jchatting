/**
 * 
 */
package com.jchatting.server;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import com.jchatting.db.DbHanddle;
import com.jchatting.db.bean.Friend;
import com.jchatting.pack.DataPackage;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-2 上午11:10:51
 */
public class OnlineTipThread extends Thread {

	private String account;
	
	private Map<String, Friend> friendMap;
	public OnlineTipThread(String account) {
		this.account = account;
		init();
	}
	
	private void init() {
		friendMap = new DbHanddle().getFriendMap(account);
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Iterator<String> keyIterator = friendMap.keySet().iterator();
		String key;
		while (keyIterator.hasNext()) {
			key = keyIterator.next();
			try {
				ServerMsgUtil.sendMsg(DataPackage.CLIENT_ON, account, key, "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
}
