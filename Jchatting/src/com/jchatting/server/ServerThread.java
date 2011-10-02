/**
 * 
 */
package com.jchatting.server;

import java.io.IOException;

import com.jchatting.db.DbHanddle;
import com.jchatting.pack.DataPackage;
import com.jchatting.util.PackageUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-24 上午10:54:43
 */
public class ServerThread extends Thread {

	private String account;
	// private Socket socket;

	// private DataInputStream reader;
	// private DataOutputStream writer;

	private DataPackage dataPackage = null;
	/**
	 * 
	 */
	public ServerThread(String account) {
		this.account = account;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				dataPackage = PackageUtil.unPackageData(ClientPool.getReader(
						account).readUTF());

				forward(dataPackage);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("ServerThread.run() IOException!");
				ClientPool.removeClient(account);
				new DbHanddle().setUserOnline(account, false);
				new OfflineTipThread(account).start();
				Thread.interrupted();
				return;
			}
		}
	}
	private void forward(DataPackage dataPackage) throws IOException {
		if (dataPackage.getType() == DataPackage.USER) {
			System.out.println("发送至用户：" + dataPackage.getReceiveId());
			System.out.println("发送内容为：" + dataPackage.getContent());

			// 用户在线，则直接发送给用户
			if (ClientPool.getWriter(dataPackage.getReceiveId()) != null) {
				ServerMsgUtil.sendMsg(dataPackage);
			}
			// 用户不在线，则存入到数据库中，作为离线消息
			else {
				System.out.println("用户不在线，保存至数据库，作为离线消息");
				if (!new DbHanddle().insertOfflineMsg(dataPackage)) {
					System.out.println("保存离线消息出错！");
				}
			}
		}
		else if (dataPackage.getType() == DataPackage.GROUP) {
			System.out.println("发送至群：" + dataPackage.getReceiveId());
			System.out.println("发送内容为：" + dataPackage.getContent());

			new SendGroupMsgThread(dataPackage).start();
		}
		else {
			System.out.println("other");
		}
	}
}
