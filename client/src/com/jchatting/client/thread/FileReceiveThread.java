/**
 * 
 */
package com.jchatting.client.thread;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jchatting.client.config.FileSocketConfig;
import com.jchatting.client.ui.ex.SendRecvDialog;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-4 下午02:59:15
 */
public class FileReceiveThread extends Thread {
	
	private SendRecvDialog sendRecvDialog;
	private FileSocketConfig fileSocketConfig;
	private File file;
	private Socket socket;
	private DataInputStream reader;
	
	public FileReceiveThread(SendRecvDialog sendRecvDialog, File fileSavePath, FileSocketConfig fileSocketConfig) {
		this.sendRecvDialog = sendRecvDialog;
		this.fileSocketConfig = fileSocketConfig;
		this.file = new File(fileSavePath, this.fileSocketConfig.getFileName());
		
		this.sendRecvDialog.setReceiveThread(this);
		
		try {
			System.out.println("file recv ip:"+ this.fileSocketConfig.getIp()+":");
			System.out.println("file recv port:"+ this.fileSocketConfig.getPort()+":");
			this.socket = new Socket(this.fileSocketConfig.getIp(), Integer.valueOf(this.fileSocketConfig.getPort()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			
			reader = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
			long sizeCount = 0;
			
			RandomAccessFile fileWriter = new RandomAccessFile(this.file, "rw");
			byte[] buffer = new byte[100 * 1024];
			int length = 0;
			while ((length = reader.read(buffer)) != -1) {
				
				fileWriter.write(buffer, 0, length);
				fileWriter.skipBytes(length);
				
				sizeCount = sizeCount + length;
				this.sendRecvDialog.update(sizeCount);
			}
			
			reader.close();
			fileWriter.close();
			
			socket.close();
			if (this.fileSocketConfig.getFileLength() == sizeCount) {
				this.sendRecvDialog.finishFileTrans();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return;
		} finally {
			if (this.sendRecvDialog != null) {
				this.sendRecvDialog.setVisible(false);
				this.sendRecvDialog.dispose();
				this.sendRecvDialog = null;
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
			socket = null;
		}
		System.out.println("file receive thread stop!");
	}
	
	public void cancel() {
		try {
			if (socket != null) {
				System.out.println("receiver socket closed");
				socket.close();
				socket = null;
			}
			if (reader != null) {
				reader.close();
				reader = null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
