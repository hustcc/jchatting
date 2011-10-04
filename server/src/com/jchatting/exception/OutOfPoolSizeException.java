/**
 * 
 */
package com.jchatting.exception;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-24 上午10:44:35
 */
public class OutOfPoolSizeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public OutOfPoolSizeException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public OutOfPoolSizeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public OutOfPoolSizeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OutOfPoolSizeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
