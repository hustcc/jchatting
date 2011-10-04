/**
 * 
 */
package com.jchatting.client.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.jchatting.pack.DataPackage;
import com.jchatting.util.PackageUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午08:57:37
 */
public class ClientMsgUtil {
	public static void sendMsg(Socket socket, DataPackage dataPackage) throws IOException {
		DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
		if (writer != null) {
			writer.writeUTF(PackageUtil.packageData(dataPackage));
		}
	}
	public static void sendMsg(Socket socket, int type, String sendId, String receiveId, String content) throws IOException {
		DataPackage dataPackage = new DataPackage(type, sendId, receiveId, content);
		sendMsg(socket, dataPackage);
	}
	public static DataPackage receiveMsg(Socket socket) throws IOException {
		DataInputStream reader = new DataInputStream(socket.getInputStream());
		return PackageUtil.unPackageData(reader.readUTF());
	}
}
