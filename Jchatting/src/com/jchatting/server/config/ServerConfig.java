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
	
	private String ip;
	//监听端口
	private int port;
	//群离线消息保留时间
	private int offlineTime;
	//最大线程数
	private int maxThread;
	private String ipConfig;
	//扫描发送配置信息的端口
	private int portConfig;
	
	
	
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

	/**
	 * @return the portConfig
	 */
	public int getPortConfig() {
		return portConfig;
	}

	/**
	 * @param portConfig the portConfig to set
	 */
	public void setPortConfig(int portConfig) {
		this.portConfig = portConfig;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the ipConfig
	 */
	public String getIpConfig() {
		return ipConfig;
	}

	/**
	 * @param ipConfig the ipConfig to set
	 */
	public void setIpConfig(String ipConfig) {
		this.ipConfig = ipConfig;
	}

	
}
