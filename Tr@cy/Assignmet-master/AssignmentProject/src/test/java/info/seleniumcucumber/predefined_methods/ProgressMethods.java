package info.seleniumcucumber.predefined_methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgressMethods {
	/**
	 * Method to wait
	 * 
	 * @param driver
	 * 
	 * @param time   : String : Time to wait
	 * @param method : String : wait by sleep or implicit method
	 * @throws NumberFormatException
	 * @throws InterruptedException
	 * 
	 */

	WebDriver driver;

	public ProgressMethods(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void wait(String time) throws NumberFormatException, InterruptedException {
		// sleep method takes parameter in milliseconds
		Thread.sleep(Integer.parseInt(time) * 250);
	}

	/**
	 * Method to Explicitly wait for element to be displayed
	 * 
	 * @param accessType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName : String : Locator value
	 * @param duration   : String : Time to wait for element to be displayed
	 */
	public void waitForElementToDisplay(String accessType, String accessName, String duration) {
		SelectElementByType selectelementbytype = new SelectElementByType(driver);
		By byEle = selectelementbytype.getelementbytype(accessType, accessName);
		WebDriverWait wait = (new WebDriverWait(driver, Integer.parseInt(duration) * 25));
		wait.until(ExpectedConditions.presenceOfElementLocated(byEle));
	}

	/**
	 * Method to Explicitly wait for element to be enabled=click
	 * 
	 * @param accessType : String : Locator type (id, name, class, xpath, css)
	 * @param accessName : String : Locator value
	 * @param duration   : String : Time to wait for element to be clickable
	 */
	public void waitForElementToClick(String accessType, String accessName, String duration) {
		SelectElementByType selectelementbytype = new SelectElementByType(driver);
		By byEle = selectelementbytype.getelementbytype(accessType, accessName);
		WebDriverWait wait = (new WebDriverWait(driver, Integer.parseInt(duration) * 1000));
		wait.until(ExpectedConditions.elementToBeClickable(byEle));
	}
}
