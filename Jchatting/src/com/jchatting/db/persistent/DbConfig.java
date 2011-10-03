package com.jchatting.db.persistent;

/**
 * 存储jdbc的连接信息
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-19 上午10:29:41
 *
 */
public class DbConfig {

	private static DbConfig instance = null;
	
	private String driver;
	private String url;
	private String userName ;
	private String password ;
	private String timeOut;
	
	public synchronized static DbConfig instance() {
		if (instance == null) {
			return instance = new DbXmlParseUtil().parseConnectXml();
		}
		return instance;
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
