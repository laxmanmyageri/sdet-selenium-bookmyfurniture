package pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import common.BasePage;
import common.CommonConstant;
import utils.ExcelUtils;

public class OrderConfirmationPage {
	
	public static final By ORDER_SUCCEESS_MESSAGE= By.xpath("//div[@class='ui-card-content']/div"); 
	public static final By ORDER_REFERENCE_ID= By.xpath("//div[@class='ui-card-content']/div/h3");
	
	WebDriver driver;
	WebElement element;
	String successMessage;
	String referenceId;
	
	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void verifyOrderSuccessMessage() throws IOException {
		element = BasePage.waitforElement(driver, driver.findElement(ORDER_SUCCEESS_MESSAGE));
		referenceId= BasePage.waitforElement(driver, driver.findElement(ORDER_REFERENCE_ID)).getText();
		successMessage=element.getText();
		Assert.assertTrue(successMessage.contains("Your order is successfully placed"), "Order is not successfull");
		String referenceNum=referenceId.substring(28);
		ExcelUtils.setCellData(CommonConstant.dataPath, "Furniture", 1, 3, referenceNum);
	}
}
