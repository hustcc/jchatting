package test;

import com.jchatting.exception.EmptyStrException;

public class UtilTest {
	public static void main(String[] args) throws EmptyStrException {
//		System.out.println(StringUtil.appendToLength("wzw", " ", 20));
//		System.out.println(StringUtil.appendToLength("wzw", " ", 20).length());
		
//		DataPackage dataPackage = new DataPackage(1, "wzwahl36", "target", "no content");
//		System.out.println(PackageUtil.packageData(dataPackage));
		String aString =  "cOnFiG!@#%&net.sourceforge.jtds.jdbc.Driver!@#%&jdbc:jtds:sqlserver://122.205.7.93:2433;DatabaseName=jchatting!@#%&*sa!@#%&*19870526!@#%&*3000!@#%&*0.0.0.0/0.0.0.0!@#%&*1235!@#%&*0.0.0.0/0.0.0.0!@#%&*1234!@#%&*";
		String[] slipted = aString.split("!@#%&");
		for (String string : slipted) {
			System.out.println(string);
		}
	}
}
