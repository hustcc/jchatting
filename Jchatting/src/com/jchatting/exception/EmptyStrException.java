/**
 * 
 */
package com.jchatting.exception;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-23 下午09:19:05
 */
public class EmptyStrException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public EmptyStrException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public EmptyStrException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public EmptyStrException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EmptyStrException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
