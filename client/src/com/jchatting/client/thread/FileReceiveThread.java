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

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-4 下午02:59:15
 */
public class FileReceiveThread extends Thread {
	private FileSocketConfig fileSocketConfig;
	private File file;
	private Socket socket;
	
	public FileReceiveThread(File fileSavePath, FileSocketConfig fileSocketConfig) {
		this.fileSocketConfig = fileSocketConfig;
		this.file = new File(fileSavePath, this.fileSocketConfig.getFileName());
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			socket = new Socket(fileSocketConfig.getIp(), Integer.valueOf(fileSocketConfig.getPort()));
			DataInputStream reader = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
			RandomAccessFile fileWriter = new RandomAccessFile(this.file, "rw");
			byte[] buffer = new byte[10 * 1024];
			int length = 0;
			while ((length = reader.read(buffer)) != -1) {
				fileWriter.write(buffer, 0, length);
				fileWriter.skipBytes(length);
			}
			reader.close();
			fileWriter.close();
			
			socket.close();
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
		System.out.println("file receive thread stop!");
		
	}
	
}
