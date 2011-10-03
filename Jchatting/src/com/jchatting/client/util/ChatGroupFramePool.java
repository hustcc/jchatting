/**
 * 
 */
package com.jchatting.client.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.jchatting.client.ui.ChatGroupFrame;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-2 下午04:05:04
 */
public class ChatGroupFramePool {

	private static Map<String, ChatGroupFrame> chatPool = new HashMap<String, ChatGroupFrame>();
	
	public synchronized static boolean disposeAllFrame() {
		Iterator<String> keys = chatPool.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			chatPool.get(key).dispose();
			chatPool.remove(key);
		}
		return true;
	}
	public synchronized static ChatGroupFrame getChatFrame(String id) {
		if (id != null) {
			return chatPool.get(id);
		}
		return null;
	}
	public synchronized static ChatGroupFrame addChatFrame(ChatGroupFrame groupFrame) {
		if (groupFrame != null && groupFrame.getUserInGroup() != null && groupFrame.getUserInGroup().getId() != null) {
			return chatPool.put(groupFrame.getUserInGroup().getId(), groupFrame);
		}
		return null;
	}
	public synchronized static ChatGroupFrame delChatFrame(String id) {
		if (id != null) {
			return chatPool.remove(id);
		}
		return null;
	}

}
