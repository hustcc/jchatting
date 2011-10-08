/**
 * 
 */
package com.jchatting.client.config;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jchatting.pack.ConfigPackage;
import com.jchatting.util.RSA;


/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-26 下午01:54:18
 */
public class ClientConfig {

	public final static String CONFIG_PACKAGE = "cOnFiG";
	public final static String CLIENT_CONFIG_FLE = "res/clientConfig.xml";
	
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
	
	/**
	 * 通过socket得到服务器安全发送的配置信息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-4 上午10:51:07
	 * @return
	 */
	private static ClientConfig getConfigFromServer() {
		ClientConfig config = new ClientConfig();
		config = parseClientXml(config);
		Socket socket = null;
		RSA rsa = null;
		try {
			socket = new Socket(config.getIpConfig(), config.getPortConfig());
			socket.setSoTimeout(30 * 1000);
			ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
			rsa = new RSA();
//			rsa.generateKey();
			RSAPublicKey publicKey = rsa.getPublicKey();
			writer.writeObject(publicKey);
			
			DataInputStream reader = new DataInputStream(socket.getInputStream());
			String configMsg = reader.readUTF();
			configMsg = rsa.decrypt(rsa.getPrivateKey(), configMsg);
			System.out.println("configMsg:" + configMsg);
			
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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rsa != null) {
				rsa.setKeyPair(null);
			}
			
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return config;
	}
	/**
	 * 获取配置文件中的配置信息
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-4 上午10:50:39
	 * @param config
	 * @return
	 */
	private static ClientConfig parseClientXml(ClientConfig config) {
		SAXReader reader=new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(CLIENT_CONFIG_FLE));
			
			Element root = document.getRootElement();
			Element ipElement = root.element("ip");
			config.setIpConfig(ipElement.getText());
			Element portElement = root.element("port");
			try {
				config.setPortConfig(Integer.valueOf(portElement.getText()));
			} catch (NumberFormatException e) {
				config.setPortConfig(1235);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("clientconfig.xml文件未找到！");
			config.setIpConfig("127.0.0.1");
			config.setPortConfig(1235);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("clientconfig.xml文件解析发生错误！");
			config.setIpConfig("127.0.0.1");
			config.setPortConfig(1235);
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
