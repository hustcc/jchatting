/**
 * 
 */
package com.jchatting.util;

import com.jchatting.exception.EmptyStrException;
import com.jchatting.pack.DataPackage;

/**
 * @author Xewee.Zhiwei.Wang
 * @version 2011-9-23 下午08:42:42
 */
public class PackageUtil {

	/**
	 * 
	 */
	public PackageUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String packageData(DataPackage dataPackage) {
		try {
			return new StringBuffer()
					.append(String.valueOf(dataPackage.getType()))
					.append(StringUtil.appendToLength(dataPackage.getSendId(), " ", 20))
					.append(StringUtil.appendToLength(dataPackage.getReceiveId(), " ", 20))
					.append(StringUtil.appendToLength(dataPackage.getContent(), " ", 20)).toString();
		} catch (EmptyStrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	public static DataPackage unPackageData(String data) {
		DataPackage dataPackage = new DataPackage();
		
		if (StringUtil.isEmpty(data)) {
			return dataPackage = null;
		}
		else {
			try {
				dataPackage.setType(Integer.valueOf(data.substring(0, 1)));
//				if (Integer.valueOf(data.substring(0, 1)) == DataPackage.USER) {
//					dataPackage.setType(DataPackage.USER);
//				}
//				else if (Integer.valueOf(data.substring(0, 1)) == DataPackage.GROUP) {
//					dataPackage.setType(DataPackage.GROUP);
//				}
//				else if (Integer.valueOf(data.substring(0, 1)) == DataPackage.SYSTEM) {
//					dataPackage.setType(DataPackage.SYSTEM);
//				}
//				else if (Integer.valueOf(data.substring(0, 1)) == DataPackage.CLIENT_ON) {
//					dataPackage.setType(DataPackage.CLIENT_ON);
//				}
//				else {
//					dataPackage.setType(DataPackage.OTHER);
//				}
				
				dataPackage.setSendId(data.subSequence(1, 21).toString().trim());
				dataPackage.setReceiveId(data.subSequence(21, 41).toString().trim());
				dataPackage.setContent(data.substring(41));
			} catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}	
		}
		return dataPackage;
	}
}
