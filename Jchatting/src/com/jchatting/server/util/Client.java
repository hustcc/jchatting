/**
 * 
 */
package com.jchatting.server.util;

import java.net.Socket;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午08:32:28
 */
public class Client {

	private String account;
	private Socket socket;
	/**
	 * 
	 */
	public Client(String account, Socket socket) {
		this.account = account;
		this.socket = socket;
	}
	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}
	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
