package common;

public class CommonConstant {
	public static String current = System.getProperty("user.dir");
	
	public static String dataPath=current+"\\src\\test\\java\\testdata\\furnitureData.xlsx";
	public static String chromeDriverPath=current+"\\drivers\\chromedriver.exe";
	public static String ieDriverPath=current+"\\drivers\\IEDriverServer.exe";
	public static String firefoxDriverPath=current+"\\drivers\\geckodriver.exe";
	public static String configFilePath=current+"\\resources\\config.properties";
	public static String reportPath=current+"\\test-output\\";
	public static String screenshotPath=current+"\\Screenshots\\PassedTestsScreenshots\\";
	public static String failedScreenshotPath=current+"\\Screenshots\\FailedTestsScreenshots\\";
}
