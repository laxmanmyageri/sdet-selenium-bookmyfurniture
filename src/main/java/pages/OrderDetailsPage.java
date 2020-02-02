package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import common.BasePage;

public class OrderDetailsPage {
	
	public static final By PLACE_ORDER_BUTTON = By.xpath("//button[contains(text(),'Place Order')]");
	public static final By CONFIRM_ORDER = By.xpath("//span[contains(text(),'Yes')]");
	
	WebDriver driver;

	public OrderDetailsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void placeOrder() {
		this.clickOnPlaceOrderButton();
		this.confirmOrder();		
	}
	
	public void clickOnPlaceOrderButton() {
		BasePage.waitforElementThenClick(driver, driver.findElement(PLACE_ORDER_BUTTON));
		BasePage.captureScreenshot(driver, "PlaceOrder");
	}
	
	public void confirmOrder() {
		BasePage.waitforElementThenClick(driver, driver.findElement(CONFIRM_ORDER));
		BasePage.captureScreenshot(driver, "ConfirmOrder");
	}

}
