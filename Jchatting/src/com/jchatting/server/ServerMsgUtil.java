/**
 * 
 */
package com.jchatting.server;

import java.io.DataOutputStream;
import java.io.IOException;

import com.jchatting.pack.DataPackage;
import com.jchatting.util.PackageUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午08:24:40
 */
public class ServerMsgUtil {
	public static void sendMsg(int type, String sendId, String receiveId, String content) throws IOException {
		DataPackage dataPackage = new DataPackage(type, sendId, receiveId, content);
		sendMsg(dataPackage);
	}
	public static void sendMsg(DataPackage dataPackage) throws IOException {
		DataOutputStream writer = ClientPool.getWriter(dataPackage.getReceiveId());
		if (writer != null) {
			if (dataPackage.getType() == DataPackage.GROUP) {
				//TODO
			}
			else {
				writer.writeUTF(PackageUtil.packageData(dataPackage));
			}
		}
	}
}
