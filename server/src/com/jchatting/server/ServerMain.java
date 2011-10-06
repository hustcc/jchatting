/**
 * 
 */
package com.jchatting.server;

import java.io.IOException;

import com.jchatting.server.config.ServerConfig;
import com.jchatting.server.thread.MonitorConfigThread;
import com.jchatting.server.thread.Server;

/**
 * 启动服务器的两个ServerSOcket线程
 * 一个用于监听客户端连接和放送消息
 * 一个用于给客户端发送客户端配置信息
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-3 下午06:35:04
 */
public class ServerMain {

	public static void main(String[] args) {
		new MonitorConfigThread(ServerConfig.instance().getPortConfig()).start();
		
		try {
			new Server().listen(ServerConfig.instance().getPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
