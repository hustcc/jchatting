/**
 * 
 */
package com.jchatting.client.thread;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-4 下午02:16:38
 */
public class FileSendThread extends Thread {

	private int initPort;
	private File fileToSend;
	private ServerSocket serverSocket;
	private Socket socket;

	public FileSendThread(File fileToSend, int initPort) {
		this.fileToSend = fileToSend;
		this.initPort = initPort;

		findAblePortAndInit();
	}
	/**
	 * 寻找可用的端口，然后实例化serverSocket
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-4 下午02:24:24
	 */
	private void findAblePortAndInit() {
		while (true) {
			try {
				serverSocket = new ServerSocket(initPort);
				serverSocket.setSoTimeout(10 * 1000);
				System.out.println("监听端口：" + initPort);
				break;
			} catch (IOException e) {
				initPort++;
				continue;
			}
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			socket = serverSocket.accept();
			DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
			FileInputStream fileInputStream = new FileInputStream(this.fileToSend);
			byte[] buffer = new byte[10 * 1024];
			int length = 0;
			while ((length = fileInputStream.read(buffer)) != -1) {
				writer.write(buffer, 0, length);
				writer.flush();
			}
			fileInputStream.close();
			writer.close();
//			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("send file thread stop");
	}
	/**
	 * @return the initPort
	 */
	public int getInitPort() {
		return initPort;
	}
	/**
	 * @param initPort the initPort to set
	 */
	public void setInitPort(int initPort) {
		this.initPort = initPort;
	}
	
	
}
