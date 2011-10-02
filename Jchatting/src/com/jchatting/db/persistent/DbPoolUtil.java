package com.jchatting.db.persistent;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DbPoolUtil {
	// 数据库连接信息
	private static DbConnectBean connectBean;

	private static ComboPooledDataSource dataSource = null;
	private static DbPoolUtil instance = null;
	private DbPoolUtil() {
		// 读取数据库连接信息
		connectBean = new DbXmlParseUtil().parseConnectXml();
		dataSource = new ComboPooledDataSource();
		// 设置jdbc连接信息
		dataSource.setUser(connectBean.userName);
		dataSource.setPassword(connectBean.password);
		dataSource.setJdbcUrl(connectBean.url);
		dataSource.setCheckoutTimeout(Integer.valueOf(connectBean.timeOut));

		try {
			dataSource.setDriverClass(connectBean.driver);
		} catch (PropertyVetoException e) {
			System.out.println("数据库驱动加载出错！");
		}
		// 设置连接池
		dataSource.setInitialPoolSize(30);
		dataSource.setMaxPoolSize(100);
		dataSource.setMinPoolSize(5);
	}

	// 为了保证单例性，必须使用同步关键字
	public synchronized static DbPoolUtil getInstance() {
		if (instance == null)
			instance = new DbPoolUtil();
		return instance;
	}

	public Connection getConnection() {
		// 使用工厂创建连接
		try {
			System.out.println("在用的连接数：" + dataSource.getNumBusyConnections());
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("数据库连接出错，请查看是否连接网络！");
			JOptionPane.showMessageDialog(null, "数据库连接出错，请查看是否连接网络！");
			System.exit(0);
			return null;
		}
	}
}
