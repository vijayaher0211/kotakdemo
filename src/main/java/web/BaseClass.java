package web;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utils.BrowserFactory;
import utils.Log;
import utils.ReadConfigFile;
import utils.ScreenshotUtils;

import org.slf4j.Logger;


/**
 * BaseClass
 *
 * Provides common setup and teardown for all web automation tests.
 * Handles browser initialization, logging, reporting, and screenshot capture.
 * All test classes should extend this class to reuse core framework features.
 */

public class BaseClass {


	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	protected Logger logger = Log.getLogger(this.getClass());

	String reportDir = ReadConfigFile.get("reportPath");
	String hostName = ReadConfigFile.get("hostName");
	String environment = ReadConfigFile.get("environment");
	String user = ReadConfigFile.get("user");
	
	@BeforeSuite(alwaysRun = true)
	public void setExtentReport() {
		
		logger.info("Initializing Extent Report...");
		
		String reportPath = System.getProperty("user.dir") + reportDir+ "/TestReport_"
				+ ScreenshotUtils.getCurrentDateTime() + ".html";
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Functional Testing");

		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", hostName);
		extent.setSystemInfo("Environment", environment);
		extent.setSystemInfo("User", user);
        logger.info("Extent Report initialized at: {}", reportPath);

	}

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		logger.info("=== Starting Test Class: " + this.getClass().getSimpleName() + " ===");
	}

	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
	public void setUp(@Optional String browserName, Method method) {
		
		logger.info("Thread {} running test method: {}", Thread.currentThread().getId(), method.getName());

	    if (browserName == null || browserName.isEmpty()) {
	        browserName = utils.ReadConfigFile.getBrowserName();
	        logger.info("No browser param, using config: {}", browserName);
	    } else {
	        logger.info("Browser param from TestNG: {}", browserName);
	    }

	    BrowserFactory.launchBrowser(browserName);
	    BrowserFactory.navigateToUrl(BrowserFactory.getDriver());

	    ExtentTest extentTest = extent.createTest(method.getName());
	    test.set(extentTest);

	    logger.info("Test started: {}", method.getName());
	}


	@AfterMethod(alwaysRun = true)
	public void teardownMethod(ITestResult result) throws IOException {
		
		WebDriver driver = BrowserFactory.getDriver();
		ExtentTest extentTest = test.get();
		
		String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());


        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                String failMessage = (result.getThrowable() != null)
                        ? result.getThrowable().getMessage()
                        : "Test Failed without exception message";
                extentTest.fail(failMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                logger.error("Test Failed: {}", failMessage);
                break;

            case ITestResult.SUCCESS:
                extentTest.pass("Test Passed Successfully",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                logger.info("Test Passed");
                break;

            case ITestResult.SKIP:
                String skipMessage = (result.getThrowable() != null)
                        ? result.getThrowable().getMessage()
                        : "Test Skipped";
                extentTest.skip(skipMessage,
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                logger.warn("Test Skipped: {}", skipMessage);
                break;
        }

		BrowserFactory.quitBrowser(driver);
        logger.info("Browser closed for test: {}", result.getName());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
        logger.info("=== Finished Test Class: " + this.getClass().getSimpleName() + " ===");
	}

	@AfterSuite(alwaysRun = true)
	public void tearDownReport() {
		if (extent != null) {
			extent.flush();
			logger.info("Extent Report flushed and saved.");
		}
	}

	
	/*
	 * Logs a step to ExtentReports with screenshot
	 */     
	public void logStep(String message) {
	    WebDriver driver = BrowserFactory.getDriver();
	    String screenshotPath = ScreenshotUtils.captureScreenshot(driver, message.replace(" ", "_"));
	    test.get().info(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    Reporter.log(message, true);
	    logger.info("Step logged: {}", message);
	}
	
    protected ExtentTest getTest() {
        return test.get();
    }

}
