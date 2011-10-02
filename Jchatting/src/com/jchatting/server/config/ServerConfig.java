/**
 * 
 */
package com.jchatting.server.config;

import com.jchatting.util.ConfigXmlParse;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-26 下午01:54:43
 */
public class ServerConfig {

	private static ServerConfig instance;
	
	//监听端口
	private int port;
	//群离线消息保留时间
	private int offlineTime;
	//最大线程数
	private int maxThread;
	
	
	public synchronized static ServerConfig instance() {
		if (instance != null) {
			return instance;
		}
		return instance = ConfigXmlParse.parseServerXml();
	}
	
	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}
	/**
	 * @return the offlineTime
	 */
	public int getOfflineTime() {
		return offlineTime;
	}
	/**
	 * @param offlineTime the offlineTime to set
	 */
	public void setOfflineTime(int offlineTime) {
		this.offlineTime = offlineTime;
	}
	/**
	 * @return the maxThread
	 */
	public int getMaxThread() {
		return maxThread;
	}
	/**
	 * @param maxThread the maxThread to set
	 */
	public void setMaxThread(int maxThread) {
		this.maxThread = maxThread;
	}

	
}
