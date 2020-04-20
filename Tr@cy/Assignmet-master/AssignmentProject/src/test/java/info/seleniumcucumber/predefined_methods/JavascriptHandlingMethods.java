package info.seleniumcucumber.predefined_methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class JavascriptHandlingMethods{
	
	WebDriver driver;
	
	public JavascriptHandlingMethods(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	/**
	 * Method to handle alert
	 * 
	 * @param decision : String : Accept or dismiss alert
	 */
	public void handleAlert(String decision) {
		if (decision.equals("accept"))
			driver.switchTo().alert().accept();
		else
			driver.switchTo().alert().dismiss();
	}
}
