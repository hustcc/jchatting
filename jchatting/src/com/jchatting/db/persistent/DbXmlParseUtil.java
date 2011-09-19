package com.jchatting.db.persistent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;



public class DbXmlParseUtil {
	private String XML_PATH = "res/dbconfig.xml";
	private DbConnectBean connectBean;
	public DbXmlParseUtil(DbConnectBean connectBean) {
		this.connectBean = connectBean;
		System.out.println(XML_PATH);
	}
	
	public DbConnectBean readConnectBean() {
		SAXReader reader=new SAXReader();
		try {
			Document document = reader.read(new FileInputStream(XML_PATH));
			
			Element databaseInfo = document.getRootElement();
			Element driverElement = databaseInfo.element("driver");
			this.connectBean.driver = driverElement.getText();
			Element urlElement = databaseInfo.element("url");
			this.connectBean.url = urlElement.getText();
		 	Element usernameElement = databaseInfo.element("user");
		 	this.connectBean.userName = usernameElement.getText();
		 	Element passwordElement = databaseInfo.element("password");
		 	this.connectBean.password = passwordElement.getText();
		 	Element timeoutElement = databaseInfo.element("timeout");
		 	this.connectBean.timeOut = timeoutElement.getText();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("databaseConnect.xml文件未找到！");
			e.printStackTrace();
			System.exit(0);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			System.out.println("databaseConnect.xml文件解析发生错误！");
			e.printStackTrace();
			System.exit(0);
		} catch (NumberFormatException e) {
			System.out.println("databaseConnect.xml解析中数据格式转换错误！");
			e.printStackTrace();
			System.exit(0);
		}
		return this.connectBean;
	}
	
	public boolean writeConnectBean() {
		
		return true;
	}
}
