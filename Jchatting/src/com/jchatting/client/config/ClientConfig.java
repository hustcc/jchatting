/**
 * 
 */
package com.jchatting.client.config;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.jchatting.pack.ConfigPackage;
import com.jchatting.util.ConfigXmlParse;


/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-26 下午01:54:18
 */
public class ClientConfig {

	public final static String CONFIG_PACKAGE = "cOnFiG";
	
	private static ClientConfig instance;
	
	private String driver;
	private String url;
	private String user;
	private String password;
	private String timeOut;
	private String ip;
	private int port;
	
	/**
	 * 以下是需要保存到clientconfig.xml文件中的配置信息
	 */
	private String ipConfig;
	private int portConfig;
	
	
	private int fontSize;
	private String fontName;
	private boolean fontBold;
	private boolean fontItalic;
	private Color fontColor;
	
	public synchronized static ClientConfig instance() {
		if (instance != null) {
			return instance;
		}
		return instance = getConfigFromServer();
	}
	
	private static ClientConfig getConfigFromServer() {
		ClientConfig config = new ClientConfig();
		config = ConfigXmlParse.parseClientXml(config);
		
		try {
			Socket socket = new Socket(config.getIpConfig(), config.getPortConfig());
			DataInputStream reader = new DataInputStream(socket.getInputStream());
			String configMsg = reader.readUTF();
//			System.out.println("configMsg:" + configMsg);
			ConfigPackage configPackage = ConfigPackage.valueOf(configMsg);
			if (configPackage != null && CONFIG_PACKAGE.equals(configPackage.getType())) {
				config.setDriver(configPackage.getDriver());
				config.setUrl(configPackage.getUrl());
				config.setUser(configPackage.getUser());
				config.setPassword(configPackage.getPassword());
				config.setIp(configPackage.getIp());
				try {
					config.setPort(Integer.valueOf(configPackage.getPort()));
				} catch (NumberFormatException e) {
					config.setPort(1234);
				}
				config.setIpConfig(configPackage.getIpConfig());
				try {
					config.setPortConfig(Integer.valueOf(configPackage.getPortConfig()));
				} catch (NumberFormatException e) {
					config.setPort(1235);
				}	
			}
			if (socket != null) {
				socket.close();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return config;
	}
	
	private ClientConfig() {
		
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
	 * @return the fontSize
	 */
	public int getFontSize() {
		return fontSize;
	}


	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}


	/**
	 * @return the fontName
	 */
	public String getFontName() {
		return fontName;
	}


	/**
	 * @param fontName the fontName to set
	 */
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}


	/**
	 * @return the fontBold
	 */
	public boolean isFontBold() {
		return fontBold;
	}


	/**
	 * @param fontBold the fontBold to set
	 */
	public void setFontBold(boolean fontBold) {
		this.fontBold = fontBold;
	}


	/**
	 * @return the fontItalic
	 */
	public boolean isFontItalic() {
		return fontItalic;
	}


	/**
	 * @param fontItalic the fontItalic to set
	 */
	public void setFontItalic(boolean fontItalic) {
		this.fontItalic = fontItalic;
	}


	/**
	 * @return the fontColor
	 */
	public Color getFontColor() {
		return fontColor;
	}


	/**
	 * @param fontColor the fontColor to set
	 */
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
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
	public int getPortConfig() {
		return portConfig;
	}

	/**
	 * @param portConfig the portConfig to set
	 */
	public void setPortConfig(int portConfig) {
		this.portConfig = portConfig;
	}

}
