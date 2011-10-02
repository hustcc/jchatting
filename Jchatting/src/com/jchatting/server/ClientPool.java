/**
 * 
 */
package com.jchatting.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.jchatting.exception.OutOfPoolSizeException;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午08:23:56
 */
public class ClientPool {

	private static HashMap<String, Client> clientPool = new HashMap<String, Client>();
	/**
	 * 
	 */
	private ClientPool() {
		// TODO Auto-generated constructor stub
	}

	public synchronized static DataOutputStream getWriter(String account)
			throws IOException {
		Client client = getClient(account);
		if (client != null) {
			Socket socket = client.getSocket();
			if (socket != null) {
				return new DataOutputStream(socket.getOutputStream());
			}
			return null;
		}
		return null;
	}
	public synchronized static DataInputStream getReader(String account)
			throws IOException {
		Client client = getClient(account);
		if (client != null) {
			Socket socket = client.getSocket();
			if (socket != null) {
				return new DataInputStream(socket.getInputStream());
			}
			return null;
		}
		return null;
	}
	/**
	 * 向线程池中添加一个元素
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-24 上午10:50:45
	 * @param socketKey
	 * @param socket
	 * @return
	 * @throws OutOfPoolSizeException
	 */
	public synchronized static Client addClient(Client client) {
		if (client != null && client.getAccount() != null
				&& client.getSocket() != null) {
			return clientPool.put(client.getAccount(), client);
		}
		return null;
	}
	/**
	 * 移除一个client
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-24 上午10:53:18
	 * @param socketKey
	 * @return
	 */
	public synchronized static Client removeClient(Client client) {
		if (client != null && client.getAccount() != null
				&& client.getSocket() != null) {
			return clientPool.remove(client.getAccount());
		}
		return null;
	}
	public synchronized static Client removeClient(String account) {
		try {
			if (account != null) {
				getClient(account).getSocket().close();
//				System.out.println("remove" + account);
				clientPool.remove(account);
//				System.out.println("get:" + clientPool.get(account));
				return null;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		return null;
	}
	/**
	 * 通过key（用户名）获得相应的client
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-24 上午10:51:16
	 * @param socketKey
	 * @return
	 */
	public synchronized static Client getClient(String account) {
		if (account != null) {
			return clientPool.get(account);
		}
		return null;
	}
	public synchronized static Client getClient(Client client) {
		if (client != null) {
			return getClient(client.getAccount());
		}
		return null;
	}

	public static int size() {
		return clientPool.size();
	}
	public synchronized static Set<String> getOnlineUsers() {
		Set<String> userSet = new HashSet<String>();
		Iterator<String> keyIterator = clientPool.keySet().iterator();
		while (keyIterator.hasNext()) {
			userSet.add(keyIterator.next());
		}
		return userSet;
	}
}
