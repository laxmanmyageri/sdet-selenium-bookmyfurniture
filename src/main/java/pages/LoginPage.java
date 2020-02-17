package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.BasePage;

public class LoginPage {
	
	public static final By LOGIN_UASERNAME_INPUT = By.id("emailId");
	public static final By LOGIN_PASSWORD_INPUT = By.id("password");
	public static final By SIGNIN_BUTTON = By.xpath("//button[contains(text(),'Sign In')]");

	WebDriver driver;
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public void setUserName(String strUserName) {
		driver.findElement(LOGIN_UASERNAME_INPUT).sendKeys(strUserName);
	}

	public void setPassword(String strPassword) {
		driver.findElement(LOGIN_PASSWORD_INPUT).sendKeys(strPassword);
	}

	public String getLoginTitle() {
		return driver.getTitle();
	}

	public void loginToBookMyFurniture(String strUserName, String strPasword) {
		this.setUserName(strUserName);
		this.setPassword(strPasword);
		BasePage.waitforElementThenClick(driver, driver.findElement(SIGNIN_BUTTON));
		BasePage.captureScreenshot(driver, "Login");
	}
}
