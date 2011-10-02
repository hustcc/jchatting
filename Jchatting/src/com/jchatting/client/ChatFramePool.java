/**
 * 
 */
package com.jchatting.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jchatting.client.ui.ChatUserFrame;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-28 下午09:36:39
 */
public class ChatFramePool {

	private static Map<String, ChatUserFrame> chatPool = new HashMap<String, ChatUserFrame>();
	
	public synchronized static boolean disposeAllFrame() {
		Iterator<String> keys = chatPool.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			chatPool.get(key).dispose();
			chatPool.remove(key);
		}
		return true;
	}
	public synchronized static ChatUserFrame getChatFrame(String account) {
		if (account != null) {
			return chatPool.get(account);
		}
		return null;
	}
	public synchronized static ChatUserFrame addChatFrame(ChatUserFrame userFrame) {
		if (userFrame != null && userFrame.getFriend() != null && userFrame.getFriend().getAccount() != null) {
			return chatPool.put(userFrame.getFriend().getAccount(), userFrame);
		}
		return null;
	}
	public synchronized static ChatUserFrame delChatFrame(String account) {
		if (account != null) {
			return chatPool.remove(account);
		}
		return null;
	}
}
