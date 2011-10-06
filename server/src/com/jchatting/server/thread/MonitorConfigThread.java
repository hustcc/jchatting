/**
 * 
 */
package com.jchatting.server.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 监视发送配置信息端口
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-3 下午06:37:30
 */
public class MonitorConfigThread extends Thread {

	private int configPort;
	private ServerSocket serverSocket;
	public MonitorConfigThread(int configPort) {
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
				new SendConfigThread(socket).start();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
						continue;
					}
				}
				continue;
			}
		}
	}
}
