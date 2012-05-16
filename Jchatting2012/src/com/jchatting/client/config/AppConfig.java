/**
 * HomePage: http://wzwahl36.zzl.org/
 * Copyright 2012 WZWAHL36.COM | HUST. 
 * All right reserved. 
 */
package com.jchatting.client.config;

/**
 * 客户端应用的配置类，包括用户自定义喜爱的配置
 * 单例模式，恶汉模式，线程安全
 */
public class AppConfig {

	private static final String APP_CONFIG_FILE_PATH = "";
	
	public static final AppConfig instance = parseFile(APP_CONFIG_FILE_PATH);
	/**
	 * 单例模式
	 */
	private AppConfig() {
		//do nothing
	}
	/**
	 * 从配置文件中解析内容
	 * @author Xewee.Zhiwei.Wang
	 * @version 2012-5-16 下午1:55:54
	 * @param filePath
	 * @return
	 */
	private static AppConfig parseFile(String filePath) {
		AppConfig appConfig = new AppConfig();
		//TODO 使用java序列化保存配置文件
		return appConfig;
	}
	/**
	 * 得到单例
	 * @author Xewee.Zhiwei.Wang
	 * @version 2012-5-16 下午1:56:16
	 * @return
	 */
	public static AppConfig instance() {
		return instance;
	}
}
