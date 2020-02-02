package test;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import common.CommonConstant;
import common.CommonFormats;
import pages.FurnituresPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.OrderDetailsPage;
import pages.PaymentPage;
import utils.ReadPropertiesFile;

public class TestNgBase {
	WebDriver driver;
	LoginPage objLogin;
	HomePage homePage;
	FurnituresPage furnituresPage;
	OrderDetailsPage orderDetailsPage;
	PaymentPage paymentPage;
	OrderConfirmationPage confirmationPage;
	OrderConfirmationPage orderConfirmationPage;
	private static Logger log=Logger.getLogger(TestNgBase.class);
	String browserName;
	String urlName;
	Properties prop;
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	@BeforeTest
	public void setExtentReport() {
		String repName="Test-Report-"+CommonFormats.timeStamp+".html";
		prop=ReadPropertiesFile.readPropertiesFromConfigFile();
		
		htmlReporter=new ExtentHtmlReporter(CommonConstant.reportPath+repName);
		htmlReporter.config().setDocumentTitle("Book My Furniture Testing");
		htmlReporter.config().setReportName("Test Automation Report"); 
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Browser",prop.getProperty("browser"));
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("Tester Name","Laxman");
	}

	@BeforeMethod
	public void setUp() {
		prop=ReadPropertiesFile.readPropertiesFromConfigFile();
		browserName=prop.getProperty("browser");
		urlName=prop.getProperty("url");
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", CommonConstant.chromeDriverPath);
			driver = new ChromeDriver();
			log.info("*****Launching Chrome Browser*****");
			
		}
		else if(browserName.equals("IE")){
			System.setProperty("webdriver.ie.driver", CommonConstant.ieDriverPath);
			 driver = new InternetExplorerDriver();
			 log.info("*****Launching IE Browser*****");
		}
		else {
			System.out.println("No browser value is given");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		driver.get(urlName);
		log.info("*****Opening Book My Furniture Website*****");
	}

	@AfterMethod
	public void closeBrowser() {
		
	}

	@AfterMethod
	public void captureScreenshot(ITestResult result) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		try {
			if (ITestResult.SUCCESS == result.getStatus()) {
				logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" ------ PASSED ", ExtentColor.GREEN)); 
				logger.addScreenCaptureFromPath(CommonConstant.screenshotPath);
			} else if (ITestResult.FAILURE == result.getStatus()) {
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("./FailedTestsScreenshots/" + "TestFailed" + ".png"));
				logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" ------ FAILED ", ExtentColor.RED)); 
				logger.fail(result.getThrowable());
				logger.addScreenCaptureFromPath(CommonConstant.failedScreenshotPath);
				log.info("*****Test Case Failed : Failure screenshot taken*****");
			} else if (ITestResult.SKIP == result.getStatus()) {
				logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
				logger.skip(result.getThrowable());
			  } 
		} catch (Exception e) {
			log.error("Exception while taking screenshot "+e);
		}
		driver.close();
		log.info("*****Closed Browser*****");
	}
	
	@AfterTest
	 public void endReport() {
	  extent.flush();
	 }
}
