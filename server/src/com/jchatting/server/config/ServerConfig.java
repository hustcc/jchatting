/**
 * 
 */
package com.jchatting.server.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-26 下午01:54:43
 */
public class ServerConfig {

	public final static String SERVER_CONFIG_FILE = "res/serverConfig.xml";
	
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
		return instance = parseServerXml();
	}
	
	private static ServerConfig parseServerXml() {
		ServerConfig config = new ServerConfig();
		SAXReader reader=new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(SERVER_CONFIG_FILE));
			
			Element root = document.getRootElement();
			Element ipElement = root.element("ip");
			config.setIp(ipElement.getText());
			Element portElement = root.element("port");
			config.setPort(Integer.valueOf(portElement.getText()));
			Element timeElement = root.element("time");
			config.setOfflineTime(Integer.valueOf(timeElement.getText()));
			Element maxThreadElement = root.element("maxThread");
			config.setMaxThread(Integer.valueOf(maxThreadElement.getText()));
			Element ipConfigElement = root.element("ipConfig");
			config.setIpConfig(ipConfigElement.getText());
			Element portConfigElement = root.element("portConfig");
			config.setPortConfig(Integer.valueOf(portConfigElement.getText()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件未找到！");
			config.setIp("127.0.0.1");
			config.setIpConfig("127.0.0.1");
			config.setPort(1234);
			config.setOfflineTime(60);
			config.setMaxThread(100);
			config.setPort(1235);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件解析发生错误！");
			config.setPort(1234);
			config.setOfflineTime(60);
			config.setMaxThread(100);
			config.setPort(1235);
		} catch (NumberFormatException e) {
			System.out.println("dbconfig.xml解析中数据格式转换错误！");
			config.setPort(1234);
			config.setOfflineTime(60);
			config.setMaxThread(100);
			config.setPort(1235);
		}
		return config;
	}
	
	private ServerConfig() {
		
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
