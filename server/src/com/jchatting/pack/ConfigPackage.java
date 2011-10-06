/**
 * 
 */
package com.jchatting.pack;

import java.io.Serializable;

import com.jchatting.util.StringUtil;

/**
 * 由服务器发送到客户端的客户端配置信息
 * 
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-3 下午06:21:20
 */
public class ConfigPackage implements Cloneable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static String CONFIG_PACKAGE = "cOnFiG";
	public final static String SPLIT_STRING = "!@#%&";
	/**
	 * 以下为保密配置信息，涉及到服务器的安全问题，每次登录连接服务器之后，右服务器发送
	 * TODO 加密发送
	 */
	private String type;//6个长度，特殊标记config
	private String driver;
	private String url;
	private String user;
	private String password;
	private String timeOut;
	private String ip;
	private String port;
	/**
	 * 以下信息为连接服务器的url，用于聊天
	 * 可以保存的xml文件中持久化，每次登录客户端前解析xml
	 * 
	 * 每次接受到服务器的配置包之后，将下面信息保存到xml中
	 */
	
	private String ipConfig;
	private String portConfig;
	
	
	public ConfigPackage() {
	}
	
	public ConfigPackage(String driver, String url, String user,
			String password, String timeOut, String ipConfig,
			String portConfig, String ip, String port) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		this.timeOut = timeOut;
		this.ipConfig = ipConfig;
		this.portConfig = portConfig;
		this.ip = ip;
		this.port = port;
	}
	public String toString() {
		return new StringBuffer().append(CONFIG_PACKAGE).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(driver) ? " " : driver).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(url) ? " " : url).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(user) ? " " : user).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(password) ? " " : password).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(timeOut) ? " " : timeOut).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(ipConfig) ? " " : ipConfig).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(portConfig) ? " " : portConfig).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(ip)? " " : ip).append(SPLIT_STRING)
		 .append(StringUtil.isEmpty(port) ? " " : port).append(SPLIT_STRING).toString();
	}
	/**
	 * 通过字符串解析出配置信息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-3 下午07:46:19
	 * @param data
	 * @return
	 */
	public static ConfigPackage valueOf(String data) {
		ConfigPackage configPackage = new ConfigPackage();
		String[] params = data.split(SPLIT_STRING);
		if (params.length == 10 && CONFIG_PACKAGE.equals(params[0])) {
			configPackage.setType(params[0]);
			configPackage.setDriver(params[1]);
			configPackage.setUrl(params[2]);
			configPackage.setUser(params[3]);
			configPackage.setPassword(params[4]);
			configPackage.setTimeOut(params[5]);
			configPackage.setIpConfig(params[6]);
			configPackage.setPortConfig(params[7]);
			configPackage.setIp(params[8]);
			configPackage.setPort(params[9]);
//			
//			for (String string : params) {
//				System.out.println(string);
//			}
//			
			return configPackage;
		}
		for (String string : params) {
			System.out.println(string);
		}
		return null;
		
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the driver
	 */
	public String getDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the timeOut
	 */
	public String getTimeOut() {
		return timeOut;
	}
	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
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
	/**
	 * @return the portConfig
	 */
	public String getPortConfig() {
		return portConfig;
	}
	/**
	 * @param portConfig the portConfig to set
	 */
	public void setPortConfig(String portConfig) {
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
	 * @return the port
	 */
	public String getPort() {
		return port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}
	
}
