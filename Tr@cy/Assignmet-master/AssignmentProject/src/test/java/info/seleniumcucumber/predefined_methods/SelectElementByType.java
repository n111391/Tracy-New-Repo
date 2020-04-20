package info.seleniumcucumber.predefined_methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectElementByType {
	// protected WebDriver driver;
	WebDriverWait wait;
	WebDriver driver;

	public SelectElementByType(WebDriver driver) {
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 30);
	}

	/**
	 * Method to select element 'by' type
	 * 
	 * @param type        : String : 'By' type
	 * @param access_name : String : Locator value
	 * @return By
	 */
	public By getelementbytype(String type, String access_name) {
		switch (type) {
		case "xpath":
			return By.xpath(access_name);
		case "css":
			return By.cssSelector(access_name);
		default:
			return null;

		}
	}
}
