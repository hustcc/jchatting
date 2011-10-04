package test;

import com.jchatting.exception.EmptyStrException;

public class UtilTest {
	public static void main(String[] args) throws EmptyStrException {
//		System.out.println(StringUtil.appendToLength("wzw", " ", 20));
//		System.out.println(StringUtil.appendToLength("wzw", " ", 20).length());
		
//		DataPackage dataPackage = new DataPackage(1, "wzwahl36", "target", "no content");
//		System.out.println(PackageUtil.packageData(dataPackage));
		String aString = "123a123";
		String[] slipted = aString.split("a");
		for (String string : slipted) {
			System.out.println(string);
		}
	}
}
