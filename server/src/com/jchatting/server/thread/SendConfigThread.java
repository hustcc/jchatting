/**
 * 
 */
package com.jchatting.server.thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.jchatting.db.persistent.DbConfig;
import com.jchatting.pack.ConfigPackage;
import com.jchatting.server.config.ServerConfig;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-3 下午06:37:30
 */
public class SendConfigThread extends Thread {

	private int configPort;
	private ServerSocket serverSocket;
	private DataOutputStream writer;
	public SendConfigThread(int configPort) {
		this.configPort = configPort;
		try {
			this.serverSocket = new ServerSocket(this.configPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			Socket socket = null;
			try {
				System.out.println("开始监听配置端口：" + this.configPort);
				socket = serverSocket.accept();

				System.out.println(socket.getInetAddress() + "请求发送配置信息！");
				this.writer = new DataOutputStream(socket.getOutputStream());
				this.writer.writeUTF(new ConfigPackage(
						DbConfig.instance().getDriver(), 
						DbConfig.instance().getUrl(), 
						DbConfig.instance().getUserName(), 
						DbConfig.instance().getPassword(), 
						DbConfig.instance().getTimeOut(),
						//todo
						"122.205.7.99", 
						String.valueOf(ServerConfig.instance().getPortConfig()),
						//todo
						"122.205.7.99", 
						String.valueOf(ServerConfig.instance().getPort()))
									.toString());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
					}
				}
				continue;
			}
		}
	}
}
