package test;

import java.awt.AWTException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import common.BasePage;
import common.CommonConstant;
import pages.FurnituresPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderConfirmationPage;
import pages.OrderDetailsPage;
import pages.PaymentPage;
import utils.ExcelUtils;
import utils.ReadPropertiesFile;
import utils.Reporting;

public class BookMyFurnitureTestCases extends TestNgBase {

	Properties prop = ReadPropertiesFile.readPropertiesFromConfigFile();
	private Logger log = Logger.getLogger(BookMyFurnitureTestCases.class);

	@Test(groups = { "regression" }, priority = 1, description = "Validate Credentials", enabled=true)
	public void validateCredentials() throws Exception {
		logger = Reporting.extent.createTest("Selenium.TC001.Validate Credentials");
		log.info("**********Selenium.TC001 - Validate Credentials**************");
		objLogin = new LoginPage(driver);
		homePage = new HomePage(driver);
		homePage.clickOnSignInLink();
		Assert.assertTrue(objLogin.getLoginTitle().contains("Book My Furniture"));
		objLogin.loginToBookMyFurniture(prop.getProperty("username"), prop.getProperty("password"));
		log.info("-----Validated Credentials-------");

		homePage.signoutApplication();
		log.info("-----This is Logout-----");
		log.info("-----Executed Selenium.TC001 - Validate Credentials------");
	}

	@Test(groups = { "regression", "functional" }, priority = 2, description = "Verify Application in new tab with Action class", enabled=false)
	public void verifyApplicationInNewTab() throws AWTException, InterruptedException {
		logger = Reporting.extent.createTest("Selenium.TC002.Verify Application in new tab");
		log.info("**********Selenium.TC002 - Verify Application in new tab**************");
		homePage = new HomePage(driver);
		homePage.openApplicationInNewTab();
		log.info("---Application Verified in new tab---");
		log.info("-----Executed Selenium.TC002 - Verify Application in new tab------");
	}
	
	@Test(groups = { "End to end" }, priority = 3, description = "Book a Furniture", enabled=true)
	public void bookAFurniture() throws Exception {
		logger = Reporting.extent.createTest("Selenium.TC003.Book a furniture");
		log.info("**********Selenium.TC004 - Book a furniture**************");
		objLogin = new LoginPage(driver);
		homePage = new HomePage(driver);
		homePage.clickOnSignInLink();
		objLogin.loginToBookMyFurniture(prop.getProperty("username"), prop.getProperty("password"));
		log.info("**********Login Successfull**************");

		homePage = new HomePage(driver);
		String furnitureType = ExcelUtils.getCellData(CommonConstant.dataPath, "Furniture", 1, 0);
		homePage.chooseFurnitureType(furnitureType);
		log.info("**********Menu Selected from home page**************");

		furnituresPage = new FurnituresPage(driver);
		String brandName = ExcelUtils.getCellData(CommonConstant.dataPath, "Furniture", 1, 1);
		furnituresPage.selectBrand(brandName);
		log.info("------Furniture selected-----");

		orderDetailsPage = new OrderDetailsPage(driver);
		orderDetailsPage.placeOrder();
		log.info("-----Order placed-----");

		paymentPage = new PaymentPage(driver);
		String paymentType = ExcelUtils.getCellData(CommonConstant.dataPath, "Furniture", 1, 2);
		System.out.println("Payment Type***** " + paymentType);
		paymentPage.makePayment(paymentType);
		log.info("-----Payment made-----");

		orderConfirmationPage = new OrderConfirmationPage(driver);
		orderConfirmationPage.verifyOrderSuccessMessage();
		log.info("-----Verified success message----");

		homePage.signoutApplication();
		log.info("----Application Logged out-----");
		log.info("**********Executed Selenium.TC003 - Book a furniture **************");
	}
	
	@Test(groups = { "regression", "functional" }, priority = 4, description = "Verify Application in new window with Action class",enabled=false)
	public void verifyApplicationInNewWindow() throws AWTException, InterruptedException {
		logger = Reporting.extent.createTest("Selenium.TC004.Verify Application in new window");
		log.info("**********Selenium.TC002 - Verify Application in new tab**************");
		homePage = new HomePage(driver);
		homePage.openApplicationInNewWindow();
		log.info("---Application Verified in new window---"); 
		log.info("-----Executed Selenium.TC004 - Verify Application in new window------");
	}
}
