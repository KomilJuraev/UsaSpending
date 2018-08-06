package gov.USAspending.utilities.tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import gov.USAspending.utilities.BrowserUtils;
import gov.USAspending.utilities.ConfigurationReader;
import gov.USAspending.utilities.Driver;

public class TestBase {

	protected WebDriver driver;
	public Actions actions;

	public ExtentReports report;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest extentLogger;

	@BeforeTest
	public void setUpTest() {
		// actual reporter
		report = new ExtentReports();
		// System.getProperty("user.dir") ---> get the path to current project
		// test-output --> folder in the current project, will be created by testng if
		// it does not already exist
		// report.html --> name of the report file
		String filePath = System.getProperty("user.dir") + "/test-output/report.html";
		htmlReporter = new ExtentHtmlReporter(filePath);

		report.attachReporter(htmlReporter);

		report.setSystemInfo("ENV", "staging");
		report.setSystemInfo("browser", ConfigurationReader.getProperty("browser"));
		report.setSystemInfo("OS", System.getProperty("os.name"));

		htmlReporter.config().setReportName("Web Orders Automated Test Reports");
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		driver = Driver.getDriver();
		actions = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();

		driver.get(ConfigurationReader.getProperty("url"));

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {
		// checking if the test method failed
		if (result != null) {

			if (result.getStatus() == ITestResult.FAILURE) {

				String screenShotLocation = BrowserUtils.getScreenshot(result.getName());
				// capture the name of the test method
				extentLogger.fail(result.getName());
				extentLogger.addScreenCaptureFromPath(screenShotLocation);
				// capture the exception thrown
				extentLogger.fail(result.getThrowable());

			} else if (result.getStatus() == ITestResult.SKIP) {
				extentLogger.skip("Test Case Skipped is " + result.getName());
			}
			Driver.closeDriver();
		}
	}

	@AfterTest
	public void tearDownTest() {
		// cleaning the reporter before the next test run
		report.flush();
	}

}
