/**
 * 
 */
package com.jchatting.server.thread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.security.interfaces.RSAPublicKey;

import com.jchatting.db.persistent.DbConfig;
import com.jchatting.pack.ConfigPackage;
import com.jchatting.server.config.ServerConfig;
import com.jchatting.util.RSA;

/**
 * 加密配置文件并发送配置的子线程
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-6 下午08:17:15
 */
public class SendConfigThread extends Thread {

	private Socket socket;
	private DataOutputStream writer;
	private ObjectInputStream reader;
	public SendConfigThread(Socket socket) {
		this.socket = socket;
	}
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.socket.setSoTimeout(30 * 1000);
			
			this.reader = new ObjectInputStream(socket.getInputStream());
			Object object = this.reader.readObject();
			RSAPublicKey publicKey = null;
			if (object instanceof RSAPublicKey) {
				publicKey = (RSAPublicKey) object;
			}
			RSA rsa = new RSA();
			
			System.out.println(socket.getInetAddress() + "请求发送配置信息！");
			this.writer = new DataOutputStream(socket.getOutputStream());
			this.writer.writeUTF(rsa.encrypt(publicKey, 
					new ConfigPackage(
					DbConfig.instance().getDriver(), 
					DbConfig.instance().getUrl(), 
					DbConfig.instance().getUserName(), 
					DbConfig.instance().getPassword(), 
					DbConfig.instance().getTimeOut(),
					//todo
					"122.205.7.99", 
					String.valueOf(ServerConfig.instance().getPortConfig()),
					//todo
					"122.205.7.99", 
					String.valueOf(ServerConfig.instance().getPort()))
								.toString()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
