package com.jchatting.db.persistent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 存储jdbc的连接信息
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-19 上午10:29:41
 *
 */
public class DbConfig {

	private final static String XML_PATH = "res/dbconfig.xml";
	
	private static DbConfig instance = null;
	
	private String driver;
	private String url;
	private String userName ;
	private String password ;
	private String timeOut;
	
	public synchronized static DbConfig instance() {
		if (instance == null) {
			return instance = parseConnectXml();
		}
		return instance;
	}
	
	private static DbConfig parseConnectXml() {
		System.out.println("Parse DbConnect Xml");
		DbConfig connectBean = new DbConfig();
		
		SAXReader reader=new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(XML_PATH));
			
			Element databaseInfo = document.getRootElement();
			Element driverElement = databaseInfo.element("driver");
			connectBean.setDriver(driverElement.getText());
			Element urlElement = databaseInfo.element("url");
			connectBean.setUrl(urlElement.getText());
		 	Element usernameElement = databaseInfo.element("user");
		 	connectBean.setUserName(usernameElement.getText());
		 	Element passwordElement = databaseInfo.element("password");
		 	connectBean.setPassword(passwordElement.getText());
		 	Element timeoutElement = databaseInfo.element("sessionTime");
		 	connectBean.setTimeOut(timeoutElement.getText());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件未找到！");
			System.exit(0);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("dbconfig.xml文件解析发生错误！");
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("dbconfig.xml解析中数据格式转换错误！");
			System.exit(0);
		}
		return connectBean;
	}
	private DbConfig() {
		
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	
	
}
