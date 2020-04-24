package reusability;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableMethods {

	public final static String testdatapath = "Test_Execution_Report/Output_Data_Excel_Report/";
	public final static String testdatafile = "Account & Order Details.xlsx";

	File file = new File("config.properties");
	File orfile = new File("ObjectRepository/page.Properties");
	File datafile = new File("inputdata.Properties");

	public String readConfig(String key) {
		Properties prop = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	public String readorConfig(String key) {
		Properties prop = null;
		try {
			FileInputStream fis = new FileInputStream(orfile);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	public String readdataConfig(String key) {
		Properties prop = null;
		try {
			FileInputStream fis = new FileInputStream(datafile);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(key);
	}

	public void setConfigValue(String key, String value) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fis);
		FileOutputStream fos = new FileOutputStream(file);
		prop.setProperty(key, value);
		prop.store(fos, null);
		fos.close();
	}

	public String newSampleText() {
		SimpleDateFormat sdf = new SimpleDateFormat("hhmmss");
		return sdf.format(new Date());
	}

	public String newDateText() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return sdf.format(new Date());
	}

	public WebDriver selectBrowser(String browser, String environment) {
		WebDriver driver = null;
		if (environment.equalsIgnoreCase("remote")) {
			try {
				if (browser.equalsIgnoreCase("chrome")) {
					System.out.println("this is chrome browser");
					System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--no-sandbox");
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					capabilities.setPlatform(Platform.MAC);
					driver = new RemoteWebDriver(new URL(readConfig("Url_Remote_Webdriver")), capabilities);
					java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					double x = screenSize.getWidth();
					double y = screenSize.getHeight();
					driver.manage().window().setSize(new Dimension((int) x, (int) y));
					driver.manage().deleteAllCookies();
					System.out.println("Chrome browser launched");
				} else if (browser.equalsIgnoreCase("firefox")) {
					System.out.println("this is firefox browser");
					DesiredCapabilities cap = new DesiredCapabilities();
					cap.setCapability("browserName", "firefox");
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("network.proxy.type", 0);
					cap.setCapability(FirefoxDriver.PROFILE, profile);
					driver = new RemoteWebDriver(new URL(readConfig("Url_Remote_Webdriver")), cap);
					java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					double x = screenSize.getWidth();
					double y = screenSize.getHeight();
					driver.manage().window().setSize(new Dimension((int) x, (int) y));
					System.out.println("firefox browser launched");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (environment.equalsIgnoreCase("local")) {
			try {
				if (browser.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", readConfig("ChromeDriver"));
					ChromeOptions options = new ChromeOptions();
					options.addArguments("--incognito");
					options.addArguments("disable-gpu");
					DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					driver = new ChromeDriver(capabilities);
					java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					double x = screenSize.getWidth();
					double y = screenSize.getHeight();
					driver.manage().window().setSize(new Dimension((int) x, (int) y));
					driver.manage().deleteAllCookies();
				} else if (browser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", readConfig("FirefoxDriver"));
					driver = new FirefoxDriver();
					java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					double x = screenSize.getWidth();
					double y = screenSize.getHeight();
					driver.manage().window().setSize(new Dimension((int) x, (int) y));
					driver.manage().deleteAllCookies();
				}else if (browser.equalsIgnoreCase("ie")) {
			          System.setProperty("webdriver.ie.driver", readConfig("IEDriver"));
			          DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
			          cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			          cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			          driver = new InternetExplorerDriver(cap);
			          java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			          double x = screenSize.getWidth();
			          double y = screenSize.getHeight();
			          driver.manage().window().setSize(new Dimension((int) x, (int) y));
			} 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return driver;
	}

	public WebDriverWait wait(WebDriver driver) {
		return new WebDriverWait(driver, 60);
	}
}
