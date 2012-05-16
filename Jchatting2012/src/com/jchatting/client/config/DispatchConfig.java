/**
 * HomePage: http://wzwahl36.zzl.org/
 * Copyright 2012 WZWAHL36.COM | HUST. 
 * All right reserved. 
 */ 
package com.jchatting.client.config;


/**
 * 中心调度服务器的配置
 * @author Xewee.Zhiwei.Wang
 * @version 2012-5-16 下午1:46:13
 */
public class DispatchConfig {
	private String ip;
	private int port;
	
	
	private static final String DISPATCH_CONFIG_FILE_PATH = "";
	
	private static DispatchConfig instance = null;
	/**
	 * 单例模式
	 */
	private DispatchConfig() {
		
	}
	
	/**
	 * 得到配置的一个实例，THREADSAFE
	 * @author Xewee.Zhiwei.Wang
	 * @version 2012-5-16 下午1:51:20
	 * @return
	 */
	public synchronized static DispatchConfig instance() {
		return instance == null ? parseFile(DISPATCH_CONFIG_FILE_PATH) : instance;
	}
	
	/**
	 * 从配置文件中解析内容//TODO
	 * @author Xewee.Zhiwei.Wang
	 * @version 2012-5-16 下午1:47:32
	 * @param file
	 */
	private static DispatchConfig parseFile(String file) {
		DispatchConfig config = new DispatchConfig();
		config.setIp("127.0.0.1");
		config.setPort(1234);
		return config;
	}

	//==============GETTER AND SETTER
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
}
