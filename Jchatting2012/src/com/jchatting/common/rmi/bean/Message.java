/**
 * 消息类
 * 
 * 采用模版方法模式
 */
package com.jchatting.common.rmi.bean;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2012-2-24 下午3:46:06
 */
public abstract class Message {
	protected abstract boolean saveToDB();
	
	/**\
	 * 定义模版
	 * @author Xewee.Zhiwei.Wang
	 * @version 2012-2-24 下午3:53:30
	 * @return
	 */
	final public boolean save() {
		return false;
	}
}
