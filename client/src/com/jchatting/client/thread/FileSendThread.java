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

import com.jchatting.client.ui.ex.SendRecvDialog;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-4 下午02:16:38
 */
public class FileSendThread extends Thread {
	
	private SendRecvDialog sendRecvDialog;
	
	private int initPort;
	private File fileToSend;
	private ServerSocket serverSocket;
	private Socket socket;
	private DataOutputStream writer;

	public FileSendThread(SendRecvDialog sendRecvDialog, File fileToSend, int initPort) {
		this.sendRecvDialog = sendRecvDialog;
		this.fileToSend = fileToSend;
		this.initPort = initPort;

		this.sendRecvDialog.setSendThread(this);
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
//				serverSocket.setSoTimeout(10 * 1000);
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
			writer = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			
			long sizeCount = 0;
			
			FileInputStream fileInputStream = new FileInputStream(this.fileToSend);
			byte[] buffer = new byte[100 * 1024];
			int length = 0;
			while (((length = fileInputStream.read(buffer)) != -1)) {
				
				writer.write(buffer, 0, length);
				writer.flush();
				sizeCount = sizeCount + length;
				
				
				this.sendRecvDialog.update(sizeCount);
			}
			fileInputStream.close();
			writer.close();
			socket.close();
			
			if (fileToSend.length() == sizeCount) {
				this.sendRecvDialog.finishFileTrans();
			}
		} catch (IOException e) {
//			e.printStackTrace();
			return;
		} finally {
			try {
				if (this.sendRecvDialog != null) {
					this.sendRecvDialog.setVisible(false);
					this.sendRecvDialog.dispose();
					this.sendRecvDialog = null;
				}
				if (socket != null) {
					socket.close();
				}
				if (serverSocket != null) {
					serverSocket.close();
				}
				socket = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
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
	
	public void cancel() {
		try {
		if (socket != null) {	
			socket.close();
			socket = null;
		}
		if (writer != null) {
			writer.close();
			writer = null;
		}
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
