package pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import common.BasePage;

public class HomePage {
	
	public static final By SIGNIN_LINK = By.xpath(".//*[@id='navbarSupportedContent']/form/button[4]");
	public static final By MENU_SOFA_LOCATOR = By.xpath("//span[contains(text(),'Sofa')]");
	public static final By SIGNIN_TEXT = By.xpath("//span[contains(text(),'Hi')]");
	public static final By SIGNOUT_LINK = By.xpath("//button[@class='mat-menu-item'][contains(text(),'Log out')]");
	public static final By CAROUSEL_CONTROL = By.xpath("//a[@class='carousel-control-prev']");                               

	WebDriver driver;
	WebElement element;
	public ExtentTest logger;

	public HomePage(WebDriver driver) { 
		this.driver = driver;
	}
	
	public void clickOnSignInLink() {
		BasePage.waitforElement(driver, driver.findElement(SIGNIN_LINK)).click();
		Assert.assertTrue(driver.findElement(LoginPage.LOGIN_UASERNAME_INPUT).isDisplayed());
	}
	
	public void signoutApplication() throws Exception {
		Thread.sleep(5000);
		BasePage.waitforElementThenClick(driver, driver.findElement(SIGNIN_TEXT));
		BasePage.waitforElement(driver, driver.findElement(SIGNOUT_LINK)).click();
	}
	
	public void chooseFurnitureType(String furnitureType) {
		element = BasePage.waitforElement(driver, driver.findElement(By.xpath("//span[contains(text(),'"+furnitureType+"')]")));
		BasePage.captureScreenshot(driver, "HomePage");
		element.click();
		Assert.assertTrue(driver.findElement(FurnituresPage.FURNITURES_LINK_TEXT).isDisplayed());
	}
	
	public void openApplicationInNewTab() throws AWTException, InterruptedException {
		Actions action= new Actions(driver);
		Robot robot=new Robot();
		action.moveToElement(driver.findElement(CAROUSEL_CONTROL));
		action.contextClick(driver.findElement(CAROUSEL_CONTROL)).sendKeys(Keys.ARROW_DOWN).build().perform();
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1)); 
		Assert.assertTrue(driver.getTitle().contains("Book My Furniture"));
		BasePage.captureScreenshot(driver, "ApplicationInNewTab");
		driver.close();
		driver.switchTo().window(tabs.get(0)); 
	}
	
	public void openApplicationInNewWindow() throws AWTException, InterruptedException {
		Actions action= new Actions(driver);
		Robot robot=new Robot();
		action.moveToElement(driver.findElement(CAROUSEL_CONTROL));
		action.contextClick(driver.findElement(CAROUSEL_CONTROL)).sendKeys(Keys.ARROW_DOWN).build().perform();
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_DOWN);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		String parentWindow = driver.getWindowHandle();
		for(String newWindow : driver.getWindowHandles()){
			  driver.switchTo().window(newWindow);
			}
		Assert.assertTrue(driver.getTitle().contains("Book My Furniture"));
		BasePage.captureScreenshot(driver, "ApplicationInNewWindow");
		driver.close();
		driver.switchTo().window(parentWindow);
	}
}
