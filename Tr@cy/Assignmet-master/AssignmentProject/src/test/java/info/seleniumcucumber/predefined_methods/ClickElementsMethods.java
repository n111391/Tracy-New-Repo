package info.seleniumcucumber.predefined_methods;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ClickElementsMethods {
	// SelectElementByType eleType= new SelectElementByType();
	private WebElement element = null;
	
	WebDriver driver;
	
	public ClickElementsMethods(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to click on an element
	 * 
	 * @param accessType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName : String : Locator value
	 */
	public void click(String accessType, String accessName) {
		SelectElementByType selectelementbytype = new SelectElementByType(driver);
		element = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
		element.click();
	}

	/**
	 * Method to forcefully click on an element
	 * 
	 * @param accessType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName : String : Locator value
	 * @throws InterruptedException 
	 */
	public void clickForcefully(String accessType, String accessName) throws InterruptedException {
		Thread.sleep(1000);
		SelectElementByType selectelementbytype = new SelectElementByType(driver);
		element = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessName)));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * Method to Double click on an element
	 * 
	 * @param accessType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName : String : Locator value
	 */
	public void doubleClick(String accessType, String accessValue) {
		SelectElementByType selectelementbytype = new SelectElementByType(driver);
		element = selectelementbytype.wait.until(ExpectedConditions.presenceOfElementLocated(selectelementbytype.getelementbytype(accessType, accessValue)));
		Actions action = new Actions(driver);
		action.moveToElement(element).doubleClick().perform();
	}
}