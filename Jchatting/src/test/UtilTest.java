package test;

import com.jchatting.exception.EmptyStrException;
import com.jchatting.pack.DataPackage;
import com.jchatting.util.PackageUtil;

public class UtilTest {
	public static void main(String[] args) throws EmptyStrException {
//		System.out.println(StringUtil.appendToLength("wzw", " ", 20));
//		System.out.println(StringUtil.appendToLength("wzw", " ", 20).length());
		
		DataPackage dataPackage = new DataPackage(1, "wzwahl36", "target", "no content");
		System.out.println(PackageUtil.packageData(dataPackage));
	}
}
