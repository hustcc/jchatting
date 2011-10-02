/**
 * 
 */
package com.jchatting.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.jchatting.server.config.ServerConfig;
import com.jchatting.util.PackageUtil;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-24 下午12:28:12
 */
public class Server {

	private ServerSocket serverSocket;

	private int port = 1234;

	/**
	 * @throws IOException
	 * 
	 */
	public Server() {
		// TODO Auto-generated constructor stub

	}

	public void listen(int port) throws IOException {

		this.port = port;
		serverSocket = new ServerSocket(this.port);
		while (true) {
			System.out.println("开始监听端口：" + this.port);
			Socket socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "客户端连接！");

			DataInputStream inputReader = new DataInputStream(socket
					.getInputStream());
			String userId = PackageUtil.unPackageData(inputReader.readUTF())
					.getSendId();
			System.out.println("客户端id：" + userId);
			System.out.println("pool中的客户端id" + ClientPool.getClient(userId));;
			if (ClientPool.getClient(userId) != null) {
				System.out.println("客户端id已经登录，通知先登录的客户端退出！");
				ClientPool.removeClient(userId);
			}
			// try {
			System.out.println("socket==null : " + socket == null);
			ClientPool.addClient(new Client(userId, socket));
			new ServerThread(userId).start();
			System.out.println("存在的客户端数量：" + ClientPool.size());
			// } catch (OutOfPoolSizeException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// socket.close();
			// }
			new OnlineTipThread(userId).start();
		}
	}

	/**
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-24 下午12:28:12
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new Server().listen(ServerConfig.instance().getPort());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
