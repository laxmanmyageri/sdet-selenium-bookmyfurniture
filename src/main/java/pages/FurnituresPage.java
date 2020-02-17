package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.BasePage;

public class FurnituresPage {
	
	public static final By NILKAMAL_SOFA_LOCATOR = By.xpath("//b[contains(text(),'Nilkamal Elena')]"); //a[contains(text(),'Next')]
	public static final By BUY_NOW_BUTTON = By.xpath("//div[@class='float-right']//button[@class='btn btn-danger space'][contains(text(),'Buy Now')]");
	public static final By LINK_TEXT_NEXT = By.xpath("//a[contains(text(),'Next')]");
	public static final By FURNITURES_LINK_TEXT = By.xpath("//a[contains(text(),'Next')]");
	
	WebDriver driver;

	public FurnituresPage(WebDriver driver) {
		this.driver = driver;
	}
	public void selectBrand(String brandName) {
		WebElement element;
		element = BasePage.waitforElement(driver, driver.findElement(By.xpath("//b[contains(text(),'"+brandName+"')]")));
		BasePage.captureScreenshot(driver, "SelectFurniture");
		/* Getting he below exception for nextPage.isDisplayed() 
		 * org.openqa.selenium.StaleElementReferenceException: 
			stale element reference: element is not attached to the page document (Document Object Model or DOM)*/
		/*nextPage = BasePage.waitforElement(driver, driver.findElement(LINK_TEXT_NEXT));
		do {
			if(element.isDisplayed()) {
				element.click();
			}else {
				nextPage.click(); 
			}
		}while(nextPage.isDisplayed() && nextPage.isEnabled());	*/	
		element.click();
	}
  
	public void clickOnBuyNowButton() {
		BasePage.waitforElementThenClick(driver, driver.findElement(BUY_NOW_BUTTON));
	}
	
}
