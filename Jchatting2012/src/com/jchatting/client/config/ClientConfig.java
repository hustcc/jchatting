/**
 * HomePage: http://wzwahl36.zzl.org/
 * Copyright 2012 WZWAHL36.COM | HUST. 
 * All right reserved. 
 */ 
package com.jchatting.client.config;

/**
 * 客户端应用连接的配置类，
 * 
 * 包括
 * 客户端连接SOCKET服务器的url和端口
 * 客户端调用RMI的IP和端口
 * 等
 * 单例模式，恶汉模式，线程安全
 */
public class ClientConfig {
	
	private String socketIp;//聊天socket服务器ip
	private int socketPort;//聊天socket服务器port
	
	private String rmiUrl;//安全连接数据库的rmi链接
	//=以上每次连接数据库时，由服务器动态分配，目地是为了均匀分布服务器负荷======//
	
	public ClientConfig() {
		
	}

	
	//==GETTER AND SETTER====
	/**
	 * @return the socketIp
	 */
	public String getSocketIp() {
		return socketIp;
	}

	/**
	 * @param socketIp the socketIp to set
	 */
	public void setSocketIp(String socketIp) {
		this.socketIp = socketIp;
	}

	/**
	 * @return the socketPort
	 */
	public int getSocketPort() {
		return socketPort;
	}

	/**
	 * @param socketPort the socketPort to set
	 */
	public void setSocketPort(int socketPort) {
		this.socketPort = socketPort;
	}

	/**
	 * @return the rmiUrl
	 */
	public String getRmiUrl() {
		return rmiUrl;
	}

	/**
	 * @param rmiUrl the rmiUrl to set
	 */
	public void setRmiUrl(String rmiUrl) {
		this.rmiUrl = rmiUrl;
	}
}
