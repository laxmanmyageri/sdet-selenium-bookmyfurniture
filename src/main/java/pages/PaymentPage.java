package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import common.BasePage;

public class PaymentPage {
	
	public static final By CREDIT_CARD_RADIO_BUTTON = By.xpath("//div[@class='ui-g']//div[1]//p-radiobutton[1]//div[1]//div[2]//span[1]");
	public static final By NET_BANKING_RADIO_BUTTON = By.xpath("//div//div[2]//p-radiobutton[1]//div[1]//div[2]//span[1]");
	public static final By CASH_RADIO_BUTTON = By.xpath("//span[@class='ui-radiobutton-icon ui-clickable pi pi-circle-on']");
	public static final By PAYMENT_PLACE_ORDER_BUTTON = By.xpath("//button[@class='btn btn-success ng-star-inserted']");
	
	WebDriver driver;
	WebElement element;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void makePayment(String paymentType) {
		this.selectPaymentOption(paymentType);
		this.clickOnPlaceOrderButton();
	}
	
	public void selectPaymentOption(String paymentType) {
		element = BasePage.waitforElement(driver, driver.findElement(By.xpath("//label[contains(text(),'"+paymentType+"')]")));
		element.click();
		BasePage.captureScreenshot(driver, "Payment");
	}
	
	public void clickOnPlaceOrderButton() {
		BasePage.waitforElementThenClick(driver, driver.findElement(PAYMENT_PLACE_ORDER_BUTTON));
	}
	

}
