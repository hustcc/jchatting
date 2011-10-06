/**
 * 
 */
package com.jchatting.util;

import java.io.File;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-10-6 下午09:42:04
 */
public class FileUtils {

	public static boolean delete(File file) {
		if (file.isFile()) {
			return file.delete();
		}
		else if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File temp : files) {
				delete(temp);
			}
			return true;
		}
		return true;
	}
//	public static void main(String[] args) {
//		System.out.println(FileUtils.delete(new File("F:/a")));
//	}
}
