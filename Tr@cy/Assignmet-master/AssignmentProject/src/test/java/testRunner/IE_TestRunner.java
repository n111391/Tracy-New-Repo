package testRunner;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import cucumber.api.CucumberOptions;
import reusability.ReusableMethods;
import reusability.Utilities;
import steps.DefinedSteps;

@RunWith(ExtendedCucumber.class)
@CucumberOptions(plugin = { "com.cucumber.listener.ExtentCucumberFormatter:" , "json:target/cucumber-reports/Cucumber.json" }, features = {
		"features" }, glue = { "steps" }, dryRun = false , monochrome = true )

public class IE_TestRunner {

	static {
		DefinedSteps.browser = "IE";
	}

	@BeforeClass
	public static void setup() throws IOException {
		ReusableMethods res = new ReusableMethods();
		Utilities util = new Utilities();
		String timeStamp = res.newDateText();
		util.deleteSheet();
		String reportPath = res.readConfig("IEReport") + timeStamp + ".html";
		File file = new File(reportPath);
		if (!file.exists()) {
			FileUtils.touch(file);
		}
		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		extentProperties.setReportPath(reportPath);
		util.createExcelReport();
	}

	@AfterClass
	public static void endTests() throws IOException {
		Reporter.loadXMLConfig(new File("extent-config.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("Machine", System.getProperty("os.name"));
		Reporter.setSystemInfo("Java Version", System.getProperty("java.runtime.version"));
		Reporter.setTestRunnerOutput("Tr@cy-Automation-Test-Report");
	}
}
