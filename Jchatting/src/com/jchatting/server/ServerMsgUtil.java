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
	/**
	 * 发送非群消息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午05:25:37
	 * @param type
	 * @param sendId
	 * @param receiveId
	 * @param content
	 * @throws IOException
	 */
	public static void sendMsg(int type, String sendId, String receiveId, String content) throws IOException {
		DataPackage dataPackage = new DataPackage(type, sendId, receiveId, content);
		sendMsg(dataPackage);
	}
	/**
	 * 发送非群消息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午05:25:26
	 * @param dataPackage
	 * @throws IOException
	 */
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
	/**
	 * 专用于发送群消息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-2 下午05:25:49
	 * @param dataPackage
	 * @param receiveId
	 * @throws IOException
	 */
	public static void sendGroupMsg(DataPackage dataPackage, String receiveId) throws IOException {
		DataOutputStream writer = ClientPool.getWriter(receiveId);
		if (writer != null) {
			if (dataPackage.getType() == DataPackage.GROUP) {
				writer.writeUTF(PackageUtil.packageData(dataPackage));
			}
		}
	}
}
