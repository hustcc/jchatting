/**
 * 
 */
package com.jchatting.client.config;

import java.awt.Color;

import com.jchatting.util.ConfigXmlParse;


/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-26 下午01:54:18
 */
public class ClientConfig {

	private static ClientConfig instance;
	
	private String ip;
	private int port;
	private int fontSize;
	private String fontName;
	private boolean fontBold;
	private boolean fontItalic;
	private Color fontColor;
	
	public synchronized static ClientConfig instance() {
		if (instance != null) {
			return instance;
		}
		return instance = ConfigXmlParse.parseClientXml();
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

}
