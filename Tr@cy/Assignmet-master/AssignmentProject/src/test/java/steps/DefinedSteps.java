package steps;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.cucumber.listener.Reporter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import info.seleniumcucumber.predefined_methods.AssertionMethods;
import info.seleniumcucumber.predefined_methods.ClickElementsMethods;
import info.seleniumcucumber.predefined_methods.InputMethods;
import info.seleniumcucumber.predefined_methods.MiscMethods;
import info.seleniumcucumber.predefined_methods.NavigateMethods;
import info.seleniumcucumber.predefined_methods.ProgressMethods;
import info.seleniumcucumber.predefined_methods.TestCaseFailed;
import reusability.ExcelUtils;
import reusability.ReusableMethods;
import reusability.Utilities;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.interactions.Actions;

/**
 * @author nareshvanteri
 */

@SuppressWarnings({ "unused" })
public class DefinedSteps {

	public static String browser = "";
	ReusableMethods res = new ReusableMethods();
	Utilities util = new Utilities();
	WebDriver driver = res.selectBrowser(browser, res.readConfig("environment"));
	ClickElementsMethods clickelementmethods = new ClickElementsMethods(driver);
	InputMethods inputObj = new InputMethods(driver);
	MiscMethods miscmethodObj = new MiscMethods(driver);
	NavigateMethods navigationObj = new NavigateMethods(driver);
	AssertionMethods assertionObj = new AssertionMethods(driver);
	ProgressMethods progressObj = new ProgressMethods(driver);
	public String url = "URL";
	public String type = res.readorConfig("type").toString(); 	
	public String type1 = res.readorConfig("type1").toString();
	public static final String dropdownselectionmessage = "Drop down option selected: ";
	  private static final String sheetname = "username";
	  private final String timeout = res.readConfig("defaulttimeout").toString();
	  private Sheet sheet;
	  private Row row;
	  private int index;
	  private static int j = 1;

	/**
	 * step to login
	 */
	
	@Given("^User launched browser and logged in to Tracy successfully$")
	public void user_navigates_to_application_login() throws Throwable {
		String duration = "3";
		try {
			driver.manage().deleteAllCookies();
			driver.get(res.readConfig("url"));
			Reporter.addStepLog("User navigated to test URL: " + url);
			String arg2 = res.readorConfig("gebruikersnaam").toString();
			String arg1 = res.readdataConfig("gebruikersnaam").toString();
			inputObj.enterText(type, arg1, arg2);
			String arg3 = res.readorConfig("wachtwoord").toString();
			String arg4 = res.readdataConfig("wachtwoord").toString();
			inputObj.enterText(type, arg4, arg3);
			String arg5 = res.readorConfig("inloggen").toString();
			clickelementmethods.click(type, arg5);
			String arg6 = res.readorConfig("inloggensuccesvol").toString();
			ProgressMethods progressObj = new ProgressMethods(driver);
			progressObj.waitForElementToDisplay(type, arg6, duration);
			driver.get("http://tcyap-ltv41.office.intern:7777/tst1/tcymain");
			Reporter.addStepLog("User launched browser and logged in to Tracy successfully" + url);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	}
	
	/**
	 * step to open menu items
	 */
	@Then("^User opens menu item \"(.*)\"$")
	public void element_to_open_menu_item(String accessName) throws Exception {
		String duration = "3";
		String aString1 = accessName.replace(" ", "").toLowerCase();

	try {
			String[] alist = aString1.split(">");
			Thread.sleep(500);
			driver.switchTo().frame(1);
			for (String list : alist) {
				String arg1 = res.readorConfig(list).toString();
				Thread.sleep(100);
				driver.findElement(By.linkText(arg1)).click();
				//clickelementmethods.click(type2, arg1);
				Thread.sleep(500);
				Reporter.addStepLog("User clicked on menu tab: " + arg1);
			}
		} catch (Exception e) {
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	}
	
	// click on web element
	@Then("^User opens tab \"(.*)\"$")
	public void open_option(String accessName) throws Exception {
		
		Thread.sleep(500);
		//driver.switchTo().frame(1);
		try {
			String finalName = accessName.replace(" ", "").toLowerCase();
			String arg1 = res.readorConfig(finalName).toString();
			clickelementmethods.click(type, arg1);
			Thread.sleep(500);
			Reporter.addStepLog("User opens tab: " + finalName);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
		}
	}

	// click on web element
	@Then("^User clicks on \"(.*)\"$")
	public void click_option(String accessName) throws Exception {

		try {
			String finalName = accessName.replace(" ", "").toLowerCase();
			String arg1 = res.readorConfig(finalName).toString();
			clickelementmethods.click(type, arg1);
			Thread.sleep(500);
			Reporter.addStepLog("User clicked on option: " + finalName);
			//System.out.println("before debiteurnummericon");
			
			if (finalName.toLowerCase().equals("debiteurnummericon")) {
				
			//	System.out.println("debiteurnummericon");
					Thread.sleep(1000);	    
				  //to get the current/parent window

				    String parentWindowContact = driver.getWindowHandle();

				    Set <String> handles =driver.getWindowHandles();
				    Iterator<String> it = handles.iterator();
				    //iterate through your windows
				    while (it.hasNext()){
				    String parent = it.next();
				    String newwin = it.next();
				    Thread.sleep(10000);
				    driver.switchTo().window(newwin);
				    Thread.sleep(2500);
				    
				    List<WebElement> componentList = driver.findElements(By.tagName("a"));
					//System.out.println(componentList.size()); 
					 
					for (WebElement component : componentList)
					{System.out.println(component.getAttribute("href"));
					
					
					    if (component.getAttribute("href").contains("javascript") && component.getText().replaceAll("[^A-Za-z0-9]","") != "geen" );
					    {
					    	String link = component.getText(); 
							 System.out.println(link + "link text");
							 System.out.println(component + "component text" );
							 	 
							if (component.getText().replaceAll("[^A-Za-z0-9]","") != "geen" ) {
							 	//component.click();
								driver.findElement(By.xpath("/html/body/table/tbody/tr[3]/td[1]/a")).click();
								 System.out.println("clicked");
								 break;
							 }
					    }
					}
				    //driver.close();
				    Thread.sleep(1000);
				    }
				    
				  //to switch back to the parent window

				    driver.switchTo().window(parentWindowContact);
				    driver.switchTo().window(parentWindowContact);        
				    driver.switchTo().frame(1);
				    Thread.sleep(1500);
				    Reporter.addStepLog("found customer using Relatienummer");
					return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
		}

		Thread.sleep(1000);
	}
	
	
	
	/**
	 * step to verify element/messages text with xpath locators
	 */
	@Then("^User performs search using \"(.*)\"$")
	public void perfor_search(String accessName) throws Exception {
		String duration = "3";
		String aString1 = accessName.replace(" ", "").toLowerCase();
		String arg3 = res.readorConfig(aString1).toString();
		String arg4 = res.readdataConfig(aString1).toString();
		String arg5 = res.readorConfig("zoeken").toString();
		
		try {
			if (aString1.toLowerCase().equals("relatienummer")) {
				
			    Thread.sleep(500);	    
			  //to get the current/parent window

			    String parentWindowContact = driver.getWindowHandle();

			    Set <String> handles =driver.getWindowHandles();
			    Iterator<String> it = handles.iterator();
			    //iterate through your windows
			    while (it.hasNext()){
			    String parent = it.next();
			    String newwin = it.next();
			    driver.switchTo().window(newwin);
			    driver.findElement(By.id(arg3)).sendKeys(arg4+"%");
			    driver.findElement(By.xpath(arg5)).click();
			    Thread.sleep(10000);
			    
			    List<WebElement> componentList = driver.findElements(By.tagName("a"));
				System.out.println(componentList.size()); 
				 
				for (WebElement component : componentList)
				{System.out.println(component.getAttribute("href"));
				
				
				    if (component.getAttribute("href").contains("javascript"))
				    {
				    	String link = component.getText(); 
						 System.out.println(link);
						 System.out.println(component);
				    	 
				        component.click();
				        System.out.println("clicked");
				       break;

				    }
				}
			    //driver.close();
			    Thread.sleep(1000);
			    }
			    
			  //to switch back to the parent window

			    driver.switchTo().window(parentWindowContact);
			    driver.switchTo().window(parentWindowContact);        
			    driver.switchTo().frame(1);
			    Thread.sleep(2500);
			    Reporter.addStepLog("found customer using Relatienummer");
				return;
			}
			
		} catch (Exception e) {
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	}
	
	/**
	   * Step to select option by text/value from dropdown
	   */
	  @Then("^User selects \"(.*?)\" from dropdown \"(.*)\"$") public void select_option_dropdown(String option, String accessName) throws Exception {
	    String finalname = accessName.replace(" ", "").toLowerCase();
	    String arg1 = res.readorConfig(finalname).toString();
	    try {
		  //  new Select(driver.findElement(By.id(arg1))).selectByVisibleText(option);
		    new Select(driver.findElement(By.id(arg1))).selectByVisibleText(option);
		    Thread.sleep(500);
		    Reporter.addStepLog(dropdownselectionmessage + option);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	  }
	  
	   /**
	   * Step to select option by text/value from dropdown
	   */
	  @Then("^User selects frame \"(.*?)\" from dropdown \"(.*)\" and clicks on \"(.*)\"$") 
	  public void select_option_frame_dropdown(String option, String accessName,String accessName2 ) throws Exception {
	    String finalname = accessName.replace(" ", "").toLowerCase();
	    String arg1 = res.readorConfig(finalname).toString();
	    try {
		  //  new Select(driver.findElement(By.id(arg1))).selectByVisibleText(option);
			Thread.sleep(1000);
			driver.switchTo().frame(1);
			Thread.sleep(1000);
		    new Select(driver.findElement(By.id(arg1))).selectByVisibleText(option);
		    Thread.sleep(2000);
	    
			String parentWindowContact = driver.getWindowHandle();
		    Set <String> handles =driver.getWindowHandles();
		    Iterator<String> it = handles.iterator();
		    //iterate through your windows
		    while (it.hasNext()){
		    String parent = it.next();
		    String newwin = it.next();
		    driver.switchTo().window(newwin);
		    Thread.sleep(1000);
		    driver.manage().window().maximize();
		    driver.close();
		    Thread.sleep(3000);
	    
		    }
	     
		    driver.switchTo().window(parentWindowContact);
		    Thread.sleep(2000);
		    driver.switchTo().window(parentWindowContact);
		    Thread.sleep(2000);
		    driver.manage().window().maximize();
		    
		    driver.switchTo().defaultContent();
		    
		/*    ((JavascriptExecutor)driver).executeScript("window.open()");
		    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		    driver.switchTo().window(tabs.get(1));		    
		    driver.get("https://www.google.com/");
		    driver.close();
		    Thread.sleep(10000); */
		    
		    driver.switchTo().window(parentWindowContact); 
		    Thread.sleep(10000);
		    
		    driver.switchTo().frame("main");
		    
		    Thread.sleep(2000);
		    
		    Actions actions = new Actions(driver);
		     //Now Select 'Rock' from sub menu which has got displayed on mouse hover of 'Music'
		     WebElement subMenuOption = driver.findElement(By.id("OG")); 
		     //Mouse hover menuOption 'Rock'
		     actions.moveToElement(subMenuOption).perform();
		    // System.out.println("Done Mouse hover on 'Rock' from Menu");
		    
		     Thread.sleep(2000);
		     driver.switchTo().frame("iframe_contractkaart");
		     Thread.sleep(2000);
		     String finalname1 = accessName2.replace(" ", "").toLowerCase();
		     String arg2 = res.readorConfig(finalname1).toString();
		     driver.findElement(By.xpath(arg2)).click();
		     
		     Thread.sleep(2500);
		     
		       Set <String> ihandles =driver.getWindowHandles();
			    Iterator<String> iit = ihandles.iterator();
			    //iterate through your windows
			    while (iit.hasNext()){
			 //  System.out.println("entered in to the second loop"); 	
			    String iparent = iit.next();
			    String inewwin = iit.next();
			    driver.switchTo().window(inewwin);
			    Thread.sleep(1000);
			    driver.manage().window().maximize();
			    Thread.sleep(2000);  
			    }
		    Reporter.addStepLog("Clicked on details and navigated to details page");
		 Reporter.addStepLog(dropdownselectionmessage + option);
			    
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			//driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	  }
	
	@Given("^User navigated to test URL$")
	public void user_navigates_to_application() throws Throwable {
		// get the dimension of screen
		try {
			driver.manage().deleteAllCookies();
			driver.get(res.readConfig("url"));
			Reporter.addStepLog("User navigated to test URL: " + url);
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	}

	// click on web element
		@Then("^User clicks on frame element \"(.*)\"$")
		public void click_frame_option(String accessName) throws Exception {

			try {
				String finalName = accessName.replace(" ", "").toLowerCase();
				String arg1 = res.readorConfig(finalName).toString();
				Thread.sleep(1000);
				driver.switchTo().frame(1);
				Thread.sleep(500);
				clickelementmethods.click(type, arg1);
				Thread.sleep(500);
			//	driver.switchTo().frame(0);
				Thread.sleep(1000);
				Reporter.addStepLog("User clicked on option: " + finalName);
			} catch (Exception e) {
				e.printStackTrace();
				Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
				driver.quit();
			}
			Thread.sleep(1000);
		}
		
	// enter text into input field steps
	@Then("^User enters \"([^\"]*)\" into \"([^\"]*)\" field$")
	public void enter_text_input(String text, String accessName) throws Exception {

		try {
			String finalinputtext = text.replace(" ", "").toLowerCase();
			String finalname = accessName.replace(" ", "").toLowerCase();
			String arg2 = res.readorConfig(finalname).toString();
			String arg1 = res.readdataConfig(finalinputtext).toString();
		      String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz0123456789abcdefgh";
		      sheet = util.getExcel();
		      Random rnd = new Random();
		      int value = rnd.nextInt(500000);
		      char string = alphabet.charAt(rnd.nextInt(alphabet.length()));
		//	System.out.println(finalinputtext + "finalinputtext");
			Thread.sleep(500);
			inputObj.enterText(type, arg1 + " " + string + value, arg2);

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	}
		
	// enter text into input field steps
		@Then("^User enters future date into \"([^\"]*)\" field$")
		public void enter_date_input(String accessName) throws Exception {
			try {
				String finalname = accessName.replace(" ", "").toLowerCase();
				String arg2 = res.readorConfig(finalname).toString();
			    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(new Date());
			    cal.add(Calendar.DATE, 5);
			    String newDate = dateFormat.format(cal.getTime());
				
				Thread.sleep(500);
				inputObj.enterText(type, newDate, arg2);
				
				 Reporter.addStepLog("Entered futher date: " + newDate);
				 
			} catch (Exception e) {
				e.printStackTrace();
				Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
				driver.quit();
				Assert.fail("this is failed because of exception " + e.getMessage());
			}
		}
	
	/**
	   * Step to enter text into input field
	   */
	  @Then("^User enter \"([^\"]*)\" into \"([^\"]*)\" field$") public void enter_text_input1(String text, String accessName) throws Exception {
	    try {
	      String finalinputtext = text.replace(" ", "").toLowerCase();
	      String finalname = accessName.replace(" ", "").toLowerCase();
	      String arg2 = res.readorConfig(finalname).toString();
	      String arg1 = res.readdataConfig(finalinputtext).toString();
	      String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz0123456789abcdefgh";
	      sheet = util.getExcel();
	      Random rnd = new Random();
	      int value = rnd.nextInt(500000);
	      char string = alphabet.charAt(rnd.nextInt(alphabet.length()));
	      if (finalinputtext.equals("regemailaddress") && finalname.equals("regemailaddress") && arg1.isEmpty()) {
	        String randomemailid = "automail" + string + value + "@maildrop.cc";
	        inputObj.enterText(type, randomemailid, arg2);
	        ExcelUtils.setExcelFile(ReusableMethods.testdatapath + ReusableMethods.testdatafile, "mailid");
	        ExcelUtils.setCellData(randomemailid, 1, 0);
	        row = sheet.createRow(j++);
	        row.createCell(1).setCellValue(randomemailid);
	        util.writeExcel();
	      } else if (accessName.toLowerCase().equals("no") || accessName.toLowerCase().equals("skip") || res.readorConfig(finalname).isEmpty() || text.toLowerCase().equals("no") || finalinputtext.toLowerCase().equals("no") || text.toLowerCase()
	          .equals("skip") || finalinputtext.toLowerCase().equals("skip")) {
	        Reporter.addStepLog("User skips the step");
	        return;
	      } else {
	        driver.manage().timeouts().implicitlyWait(Long.parseLong(timeout), TimeUnit.SECONDS);
	        inputObj.enterText(type, arg1, arg2);
	      }
	    } catch (Exception e) {
	      Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
	      driver.quit();
	      Assert.fail("this is failed because of exception "  + e.getMessage());
	    }
	  }

	  
	/**
	 * step to verify element/messages text with xpath locators
	 */	
	@Then("^User completes the steps keuze->Opslaan$")
	public void switchToFrameByIdOrName() throws InterruptedException {
		
		clickelementmethods.click(type, "//*[@id='a_keuzes']");
		//driver.findElement(By.id("a_keuzes")).click();
		 Reporter.addStepLog("Clicked keuzes option");
		Thread.sleep(1000);
		driver.switchTo().frame("pdl_keuzes_frame");
		new Select(driver.findElement(By.id("21484_dow_id"))).selectByVisibleText("SLA: Service Standaard");
		Reporter.addStepLog("Selected SLA: Service Standaard");
		driver.switchTo().parentFrame();
	    driver.findElement(By.id("a_opties")).click();
	    Reporter.addStepLog("Clicked opties option");
	    driver.switchTo().frame("pdl_opties_frame");
	    driver.findElement(By.id("22860")).click();
	    driver.findElement(By.xpath("//form[@id='optiesform']/table/tbody/tr[3]/td[2]")).click();
	    Reporter.addStepLog("Checked opties checkbox");
	    new Select(driver.findElement(By.id("22860_dow_id"))).selectByVisibleText("1 IP-adres");
	    Reporter.addStepLog("Selected 1 IP-adres");
	    driver.switchTo().parentFrame();
	    driver.findElement(By.xpath("//input[@value='Opslaan']")).click();
	    Reporter.addStepLog("Clicked Opslaan option");
	}
	
	@Then("^User should see the \"(.*)\" message$")
	public void element_to_display(String accessName) throws Exception {
		String duration = "3";
		String aString1 = accessName.replace(" ", "").toLowerCase();

		if (accessName.toLowerCase().equals("no") || aString1.toLowerCase().equals("no")
				|| accessName.toLowerCase().equals("skip") || aString1.toLowerCase().equals("skip")) {
			Reporter.addStepLog("User skips the step");
			return;
		}
		if (aString1.toLowerCase().equals("inloggensuccesvol")) {
			
			//WebElement textbox = driver.findElement(By.xpath("//input[@value = 'Inloggen']"));
			//textbox.sendKeys(Keys.RETURN);
			Thread.sleep(5000);
			driver.get("http://tcyap-ltv41.office.intern:7777/tst1/tcymain");
			//driver.get("http://tcyap-ltv41.office.intern:7777/tst1/tcy_730.show_insertform?p_functie=intake_case_sales&p_srt_id=401&p_ckd_id=1");
			Thread.sleep(1000);
			driver.switchTo().frame(1);
			driver.findElement(By.linkText("Aanmaken")).click();
			Thread.sleep(500);
			driver.findElement(By.linkText("Contractkaart")).click();
			Thread.sleep(500);
		    driver.findElement(By.linkText("Office Plus")).click();
		    Thread.sleep(500);
		    driver.findElement(By.linkText("Nieuw")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("a_aanvraag")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("a_contracten")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("td_contractkaart")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("a_contractkaart")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("a_koppelingen")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("a_opmerkingen")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("a_documenten")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("a_history")).click();
		    Thread.sleep(500);
		    driver.findElement(By.id("a_relatie")).click();
		    Thread.sleep(500);
		    
		    
		    new Select(driver.findElement(By.id("p_acr_id"))).selectByVisibleText("B2B D2D");
		    Thread.sleep(500);
		    driver.findElement(By.id("p_dealer_fee_percentage")).click();
		    Thread.sleep(500);
		    new Select(driver.findElement(By.id("p_dealer_fee_percentage"))).selectByVisibleText("50 %");
		    driver.findElement(By.id("p_dealer_fee_percentage")).click();
		    
		    driver.findElement(By.xpath("//a[contains(@title, 'Kies een relatie')]")).click();
		    Thread.sleep(1000);
		    
		  //to get the current/parent window
		    String parentWindowContact = driver.getWindowHandle();
		    Set <String> handles =driver.getWindowHandles();
		    Iterator<String> it = handles.iterator();
		    //iterate through your windows
		    while (it.hasNext()){
		    String parent = it.next();
		    String newwin = it.next();
		    driver.switchTo().window(newwin);
		    //perform actions on new window
		    driver.findElement(By.id("z_relatie_kvk_nr")).click();
		    driver.findElement(By.id("z_relatie_ebs_nr")).click();
		    driver.findElement(By.id("z_debnr")).click();
		    driver.findElement(By.id("z_relatie_naam")).click();
		    driver.findElement(By.id("z_relatie_nummer")).sendKeys("%10098228%");
		    driver.findElement(By.xpath("//input[@value='Zoeken']")).click();
		    Thread.sleep(10000);
		    
		    List<WebElement> componentList = driver.findElements(By.tagName("a"));
			System.out.println(componentList.size()); 
			 
			for (WebElement component : componentList)
			{System.out.println(component.getAttribute("href"));
			
			
			    if (component.getAttribute("href").contains("javascript"))
			    {
			    	String link = component.getText(); 
					 System.out.println(link);
					 System.out.println(component);
			    	 
			        component.click();
			        System.out.println("clicked");
			       break;

			    }
			}
		    //driver.close();
		    Thread.sleep(1000);
		    }
		    
		  //to switch back to the parent window

		    driver.switchTo().window(parentWindowContact);
		    driver.switchTo().window(parentWindowContact);
		        
		    driver.switchTo().frame(1);
		    Thread.sleep(1000);
		    /*
		    
		    Thread.sleep(5000);
		    driver.switchTo().window(winHandleBefore);
		    
		    */
		    Thread.sleep(2000);
		    new Select(driver.findElement(By.id("p_acr_id"))).selectByVisibleText("B2B D2D");
		    Thread.sleep(500);
			Reporter.addStepLog("User navigated to Rapportages > Daily Dashboard page");
			return;
		}

		try {
			String[] alist = aString1.split(",");
			for (String list : alist) {
				String arg1 = res.readorConfig(list).toString();
				ProgressMethods progressObj = new ProgressMethods(driver);
				progressObj.waitForElementToDisplay(type, arg1, duration);
				Reporter.addStepLog("message verified: " + list);
			}
		} catch (Exception e) {
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	}
	
	
	/**
	 * page title checking
	 *
	 * @param present :
	 * @param title   :
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Then("^User should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
	public void check_title(String present, String title) throws TestCaseFailed, InterruptedException, IOException {
		// System.out.println("Present :" + present.isEmpty());
		try {
			Thread.sleep(1000);
			assertionObj.checkTitle(title, present.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	}

	// step to check element partial text
	@Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
	public void check_partial_text(String present, String partialTextTitle)
			throws TestCaseFailed, InterruptedException, IOException {
		try {
			// System.out.println("Present :" + present.isEmpty());
			Thread.sleep(1000);
			assertionObj.checkPartialTitle(partialTextTitle, present.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addScreenCaptureFromPath(util.screenCapture(driver));
			driver.quit();
			Assert.fail("this is failed because of exception " + e.getMessage());
		}
	}

	// reset webpage view use

	@Then("^I reset page view$")
	public void reset_page_zoom() {
		navigationObj.zoomInOut("reset");
	}

	// scroll webpage

	@Then("^I scroll to (top|end) of page$")
	public void scroll_page(String to) throws Exception {
		navigationObj.scrollPage(to);
	}

	// Switch between frame

	// Step to switch to frame by web element
	@Then("^I switch to frame having (.+) \"(.*?)\"$")
	public void switch_frame_by_element(String method, String value) {
		navigationObj.switchFrame(method, value);
	}

	// step to switch to main content
	@Then("^I switch to main content$")
	public void switch_to_default_content() {
		navigationObj.switchToDefaultContent();
	}

	// Step to scrool down page vertically
	@Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
	public void resize_browser(int width, int heigth) {
		navigationObj.resizeBrowser(width, heigth);
	}

	// To interact with browser

	// step to resize browser
	@Then("^I scrool down window vertically (\\d+) and (\\d+)$")
	public void scrool_vertically(int xpixels, int ypixels) {
		navigationObj.scrooldownvertically(xpixels, ypixels);
	}

	// step to maximize browser
	@Then("^I maximize browser window$")
	public void maximize_browser() {
		navigationObj.maximizeBrowser();
	}

	// Step to close the browser
	@Then("^I close browser$")
	public void close_browser() {
		navigationObj.closeDriver();
	}

	@Then("^Close the application$")
	public void close_the_application() throws Throwable {
		driver.quit();
	}

	// zoom in/out page

	// steps to zoom in page
	@Then("^I zoom in page$")
	public void zoom_in() {
		navigationObj.zoomInOut("ADD");
	}

	// steps to zoom out page
	@Then("^I zoom out page$")
	public void zoom_out() {
		navigationObj.zoomInOut("SUBTRACT");
	}

	// Step to navigate forward
	@Then("^I navigate forward")
	public void navigate_forward() {
		navigationObj.navigate("forward");
	}

	// Step to navigate backward
	@Then("^I navigate back")
	public void navigate_back() {
		navigationObj.navigate("back");
	}

	// steps to refresh page
	@Then("^I refresh page$")
	public void refresh_page() {
		driver.navigate().refresh();
	}

	// Switch to new window
	@Then("^I switch focus to new window$")
	public void switch_to_new_window() throws InterruptedException {
		Thread.sleep(5000);
		navigationObj.switchToNewWindow();
	}

	// Switch to old window
	@Then("^I switch to previous window$")
	public void switch_to_old_window() throws InterruptedException {
		Thread.sleep(5000);
		navigationObj.switchToOldWindow();
	}

	// Switch to new window by window title
	@Then("^I switch to window having title \"(.*?)\"$")
	public void switch_to_window_by_title(String windowTitle) throws Exception {
		navigationObj.switchToWindowByTitle(windowTitle);
	}

	// Close new window
	@Then("^I close new window$")
	public void close_new_window() {
		navigationObj.closeNewWindow();
	}

	// step to assert javascript pop-up alert text
	@Then("^I should see alert text as \"(.*?)\"$")
	public void check_alert_text(String actualValue) throws TestCaseFailed {
		assertionObj.checkAlertText(actualValue);
	}
	// Progress methods

	// wait for specific period of time
	@Then("^I wait for (\\d+) sec$")
	public void wait(String time) throws NumberFormatException, InterruptedException {
		try {
			progressObj.wait(time);
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

}