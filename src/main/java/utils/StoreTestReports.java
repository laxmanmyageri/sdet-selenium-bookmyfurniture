package utils;

import java.io.File;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import common.CommonFormats;

public class StoreTestReports {

	static Properties prop;

	public static void copyReportsToSharedPath() {
		try {
			prop = ReadPropertiesFile.readPropertiesFromConfigFile();
			String destinationDir;
			destinationDir = "//E6ML15705/SharedFolder" + File.separator + prop.getProperty("sharedFolder") + File.separator + CommonFormats.timeStamp;
			String sourceDir = System.getProperty("user.dir") + File.separator + "test-output";
			File destDir = new File(destinationDir);
			File srcDir = new File(sourceDir);
			FileUtils.copyDirectory(srcDir, destDir);
			System.out.println("Results are copied from location >>> " + sourceDir);
			System.out.println("Results are copied to location >>> " + destinationDir);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
