package test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import common.CommonConstant;
import pages.FurnituresPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.OrderDetailsPage;
import pages.PaymentPage;
import utils.ReadPropertiesFile;
import utils.Reporting;
import utils.StoreTestReports;

public class TestNgBase {
	WebDriver driver;
	LoginPage objLogin;
	HomePage homePage;
	FurnituresPage furnituresPage;
	OrderDetailsPage orderDetailsPage;
	PaymentPage paymentPage;
	OrderConfirmationPage confirmationPage;
	OrderConfirmationPage orderConfirmationPage;
	private static Logger log = Logger.getLogger(TestNgBase.class); 
	String browserName;
	String urlName;
	Properties prop;
	Reporting extentReport;
	public static ExtentTest logger;
	Connection conn = null;
	Statement stmt = null;
	ResultSet resultSet = null;

	@BeforeSuite
	public void setExtentReport() throws ClassNotFoundException, SQLException {
		prop = ReadPropertiesFile.readPropertiesFromConfigFile();
		extentReport = new Reporting();
		extentReport.setExtentReport();
		
		//Database Connection
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(prop.getProperty("dbPath"), prop.getProperty("dbUsername"), prop.getProperty("dbPassword"));
	}

	@BeforeMethod
	public void setUp() {
		prop = ReadPropertiesFile.readPropertiesFromConfigFile();
		browserName = prop.getProperty("browser");
		urlName = prop.getProperty("url");
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", CommonConstant.chromeDriverPath);
			driver = new ChromeDriver();
			log.info("*****Launching Chrome Browser*****");
		} else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver", CommonConstant.ieDriverPath);
			driver = new InternetExplorerDriver();
			log.info("*****Launching IE Browser*****");
		} else if (browserName.equals("firefox")){
			System.setProperty("webdriver.firefox.marionette", CommonConstant.firefoxDriverPath);
			driver = new FirefoxDriver();
			log.info("*****Launching Firefox Browser*****");
		} else {
			System.out.println("No browser value is given");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.get(urlName);
		log.info("*****Opening Book My Furniture Website*****");
	}

	@AfterMethod
	public void captureScreenshot(ITestResult result) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		try {
			if (ITestResult.SUCCESS == result.getStatus()) {
				logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " ------ PASSED", ExtentColor.GREEN));
			} else if (ITestResult.FAILURE == result.getStatus()) {
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File(CommonConstant.failedScreenshotPath + "TestFailed" + ".png"));
				logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " ------ FAILED", ExtentColor.RED));
				logger.fail(result.getThrowable());
				logger.pass("[Screenshots] Failed:", MediaEntityBuilder.createScreenCaptureFromPath(CommonConstant.failedScreenshotPath+"TestFailed.png").build());
				log.info("*****Test Case Failed : Failure screenshot taken*****");
			} else if (ITestResult.SKIP == result.getStatus()) {
				logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " ------ SKIPPED", ExtentColor.ORANGE));
				logger.skip(result.getThrowable());
			}
		} catch (Exception e) {
			log.error("Exception while taking screenshot " + e);
		}
		driver.close();
		log.info("*****Closed Browser*****");
	}

	@AfterSuite
	public void endReport() {
		if (conn != null) {
			 try {
			 conn.close();
			 } catch (Exception e) {
				 log.error(e);
			 }
			 }
		extentReport = new Reporting();
		extentReport.endExtentReport();
		StoreTestReports.copyReportsToSharedPath();
	}
}
