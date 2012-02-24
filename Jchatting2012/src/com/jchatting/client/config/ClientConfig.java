/**
 * 客户端应用的配置类，
 * 
 * 包括
 * 客户端连接服务器的url和端口
 * 客户端调用rmi的url和端口
 * 等
 * 单例模式，恶汉模式，线程安全
 */
package com.jchatting.client.config;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2012-2-24 下午2:37:37
 */
public class ClientConfig {
private static final String CLIENT_CONFIG_FILE_PATH = "";
	
	public static final ClientConfig instance = parseFile(CLIENT_CONFIG_FILE_PATH);
	
	private ClientConfig() {
		
	}
	
	private static ClientConfig parseFile(String filePath) {
		ClientConfig clientConfig = new ClientConfig();
		//TODO 使用java解析xml文件
		return clientConfig;
	}
	
	public static ClientConfig getConfig() {
		return instance;
	}
}
