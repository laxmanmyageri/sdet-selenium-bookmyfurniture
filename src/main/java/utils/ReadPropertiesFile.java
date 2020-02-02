package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import common.CommonConstant;

public class ReadPropertiesFile {

	public static Properties readPropertiesFromConfigFile() {
		Properties prop= new Properties();
		try {
			InputStream input=new FileInputStream(CommonConstant.configFilePath);
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
