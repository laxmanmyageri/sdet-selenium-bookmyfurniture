package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;

public class HomePage {
	
	public static final By SIGNIN_LINK = By.xpath(".//*[@id='navbarSupportedContent']/form/button[4]");
	public static final By MENU_SOFA_LOCATOR = By.xpath("//span[contains(text(),'Sofa')]");
	public static final By SIGNIN_TEXT = By.xpath("//span[contains(text(),'Hi')]");
	public static final By SIGNOUT_LINK = By.xpath("//button[@class='mat-menu-item'][contains(text(),'Log out')]");
	

	
	WebDriver driver;
	WebElement element;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnSignInLink() {
		BasePage.waitforElement(driver, driver.findElement(SIGNIN_LINK)).click();
	}
	
	public void signoutApplication() throws Exception {
		Thread.sleep(5000);
		BasePage.waitforElementThenClick(driver, driver.findElement(SIGNIN_TEXT));
		BasePage.waitforElement(driver, driver.findElement(SIGNOUT_LINK)).click();
	}
	
	public void chooseFurnitureType(String furnitureType) {
		element = BasePage.waitforElement(driver, driver.findElement(By.xpath("//span[contains(text(),'"+furnitureType+"')]")));
		element.click();		
	}
}
