package common;

public class CommonConstant {
	public static String current = System.getProperty("user.dir");
	
	public static String expectedCreatedMsg="Created";
	public static String expectedFoundMsg="Product found";
	
	//Paths
	public static String dataPath = current+"\\src\\test\\java\\testdata\\furnitureData.xlsx";
	public static String chromeDriverPath = current+"\\drivers\\chromedriver.exe";
	public static String ieDriverPath = current+"\\drivers\\IEDriverServer.exe";
	public static String firefoxDriverPath = current+"\\drivers\\geckodriver.exe";
	public static String configFilePath = current+"\\resources\\config.properties";
	public static String reportPath = current+"\\test-output\\";
	public static String passedScreenshotPath = current+"\\test-output\\Screenshots\\PassedTestsScreenshots\\";
	public static String failedScreenshotPath = current+"\\test-output\\Screenshots\\FailedTestsScreenshots\\";	
}
