/**
 * 客户端应用的配置类，包括用户自定义喜爱的配置
 * 单例模式，恶汉模式，线程安全
 */
package com.jchatting.client.config;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2012-2-24 下午2:24:22
 */
public class AppConfig {

	private static final String APP_CONFIG_FILE_PATH = "";
	
	public static final AppConfig instance = parseFile(APP_CONFIG_FILE_PATH);
	
	private AppConfig() {
		//do nothing
	}
	
	private static AppConfig parseFile(String filePath) {
		AppConfig appConfig = new AppConfig();
		//TODO 使用java序列化保存配置文件
		return appConfig;
	}
	
	public static AppConfig getConfig() {
		return instance;
	}
}
