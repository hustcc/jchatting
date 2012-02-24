/**
 * 用户帐号密码验证不通过抛出异常
 */
package com.jchatting.common.exception;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2012-2-24 下午3:19:52
 */
public class IllegalUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalUserException() {
		super();
	}

	public IllegalUserException(String message) {
		super(message);
	}

}
