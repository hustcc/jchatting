/**
 * 服务器数据库连接配置
 */
package com.jchatting.server.config;


/**
 * @author Xewee.Zhiwei.Wang
 * @version 2012-2-24 下午2:51:40
 */
public class DbConfig {
private static final String APP_CONFIG_FILE_PATH = "";
	
	public static final DbConfig instance = parseFile(APP_CONFIG_FILE_PATH);
	
	private DbConfig() {
		//do nothing
	}
	
	private static DbConfig parseFile(String filePath) {
		DbConfig dbConfig = new DbConfig();
		//TODO 使用java解析xml配置文件
		return dbConfig;
	}
	
	public static DbConfig getConfig() {
		return instance;
	}
}
