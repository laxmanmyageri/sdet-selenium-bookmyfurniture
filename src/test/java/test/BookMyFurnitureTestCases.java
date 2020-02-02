package test;

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

public class BookMyFurnitureTestCases extends TestNgBase {
	
	Properties prop=ReadPropertiesFile.readPropertiesFromConfigFile();
	private Logger log=Logger.getLogger(BookMyFurnitureTestCases.class);
	
	@Test(priority = 1, description = "Validate Credentials")
	public void validateCredentials() throws Exception {
		logger = extent.createTest("Test Case 1 - Validate Credentials");
		log.info("**********Running Test Case 1 - Validate Credentials**************");
		objLogin = new LoginPage(driver);
		homePage = new HomePage(driver);
		homePage.clickOnSignInLink();
		Assert.assertTrue(objLogin.getLoginTitle().contains("Book My Furniture"));
		objLogin.loginToBookMyFurniture(prop.getProperty("username"), prop.getProperty("password"));
		BasePage.captureScreenshot(driver, "VerifyLogin");
		log.info("**********Validated Credentials**************");
		
		homePage.signoutApplication();
		log.info("**********This is Logout**************");
		log.info("**********Executed Test Case 1 - Validate Credentials **************");
	}

	@Test(priority = 2, description = "Book a Furniture")
	public void bookASofa() throws Exception {
		logger = extent.createTest("Test Case 2 - Book a furniture");
		objLogin = new LoginPage(driver);
		homePage = new HomePage(driver);
		homePage.clickOnSignInLink();
		Assert.assertTrue(objLogin.getLoginTitle().contains("Book My Furniture"));
		objLogin.loginToBookMyFurniture(prop.getProperty("username"), prop.getProperty("password"));
		BasePage.captureScreenshot(driver, "Login");
		log.info("**********Login Successfull**************");
		
		homePage = new HomePage(driver);
		String furnitureType=ExcelUtils.getCellData(CommonConstant.dataPath, "Furniture", 1, 0);
		System.out.println("FurnitureType***** "+furnitureType);
		homePage.chooseFurnitureType(furnitureType);
		log.info("**********Selecting menu from home page**************");
		BasePage.captureScreenshot(driver, "HomePage");
		
		furnituresPage = new FurnituresPage(driver);
		String brandName=ExcelUtils.getCellData(CommonConstant.dataPath, "Furniture", 1, 1);
		System.out.println("Brand name***** "+brandName);
		furnituresPage.selectBrand(brandName);
		log.info("**********Selecting furniture**************");
		furnituresPage.clickOnBuyNowButton();
		log.info("**********Buying furniture**************");
		BasePage.captureScreenshot(driver, "SelectSofa");
		
		orderDetailsPage = new OrderDetailsPage(driver);
		orderDetailsPage.placeOrder();
		log.info("**********placing order**************");
		
		paymentPage = new PaymentPage(driver);
		String paymentType=ExcelUtils.getCellData(CommonConstant.dataPath, "Furniture", 1, 2);
		System.out.println("Payment Type***** "+paymentType);
		paymentPage.makePayment(paymentType);
		log.info("**********Making Payment**************");
		
		orderConfirmationPage=new OrderConfirmationPage(driver);
		orderConfirmationPage.verifyOrderSuccessMessage();
		log.info("**********Verifying success message**************");
		BasePage.captureScreenshot(driver, "OrderConfirmation");
		
		homePage.signoutApplication();
		log.info("**********This is Logout**************");
	}
}
