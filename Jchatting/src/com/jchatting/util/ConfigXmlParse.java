/**
 * 
 */
package com.jchatting.util;

import com.jchatting.client.config.ClientConfig;
import com.jchatting.server.config.ServerConfig;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-26 下午01:51:27
 */
public class ConfigXmlParse {

	public final static String SERVER_CONFIG_FILE = "res/serverConfig.xml";
	public final static String CLIENT_CONFIG_FLE = "res/clientConfig.xml";
	
	public static ClientConfig parseClientXml() {
		ClientConfig config = new ClientConfig();
		//TODO
		config.setIp("127.0.0.1");
		config.setPort(1234);
		
		return config;
	}
	
	public static ServerConfig parseServerXml() {
		ServerConfig config = new ServerConfig();
		//TODO
		config.setPort(1234);
		config.setOfflineTime(60);
		config.setMaxThread(100);
		return config;
	}

}
