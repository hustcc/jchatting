/**
 * 
 */
package com.jchatting.client;

import java.awt.EventQueue;

import com.jchatting.client.ui.LoginFrame;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-3 下午08:48:19
 */
public class ClientMain {

	/**
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-3 下午08:48:19
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
