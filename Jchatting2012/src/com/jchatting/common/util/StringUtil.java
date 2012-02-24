/**
 * 
 */
package com.jchatting.common.util;

import com.jchatting.common.exception.EmptyStrException;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-23 下午09:09:01
 */
public class StringUtil {

	/**
	 * 
	 */
	public StringUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 当string的长度超过limitLength时，将后面的字符串代替成replaceChars；
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-10-5 下午06:25:37
	 * @param string
	 * @param limitLength
	 * @param replaceChars
	 * @return
	 */
	public static String replaceLengthChars(String string, int limitLength, String replaceChars) {
		return string.length() > limitLength ? string.substring(0, limitLength) + replaceChars : string;
	}
	/**
	 * 判断str是否为空，即长度为0或者NULL
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-23 下午09:34:44
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	/**
	 * 使用chars填充src字符串到长度length
	 * 如果大于length，则截取
	 * 如果小于length，则填充
	 * 
	 * @author Xewee.Zhiwei.Wang
	 * @version 2011-9-23 下午09:32:05
	 * @param src
	 * @param chars
	 * @param length
	 * @return
	 * @throws EmptyStrException
	 */
	public static String appendToLength(String src, String chars, int length) throws EmptyStrException {
		if (isEmpty(chars)) {
			throw new EmptyStrException("String is NULL or String length is 0 !");
		}
		
		String returnStr = "";
		//原始字符串为null，则复制chars为length，返回
		if (src == null) {
			for (int i = 0; i < length; i++) {
				returnStr = returnStr + chars;
				if (returnStr.length() >= length) {
					return returnStr.subSequence(0, length).toString();
				}
			} 
		}
		else {
			returnStr = "";
			int srcLen = src.length();
			if (srcLen >= length) {
				return src.subSequence(0, 20).toString();
			}
			else {
				returnStr = src;
				for (int i = 0; i < length - srcLen; i++) {
					returnStr = returnStr + chars;
					if (returnStr.length() >= length) {
						return returnStr.subSequence(0, length).toString();
					}
				}
			}
		}
		return src;
	}
}
