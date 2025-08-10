package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utils.BrowserFactory;
import utils.Log;
import utils.ScreenshotUtils;
import web.BaseClass;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;


/**
 * TestListener
 * 
 * Implements TestNG's ITestListener interface to listen to test execution events.
 * 
 * Provides logging for test suite and test case start, success, failure, and skip events.
 * Captures and logs screenshots on test success, failure, and skip using Selenium WebDriver.
 * 
 * Helps in detailed reporting and debugging by capturing real-time execution flow and screenshots.
 */

public class TestListener implements ITestListener {

	private static final Logger logger = Log.getLogger(TestListener.class);


    @Override
    public void onStart(ITestContext context) {
        logger.info("Test Suite started: {}", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test Suite finished: {}", context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Test started: {}", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: {}", result.getName(), result.getThrowable());
        Object currentClass = result.getInstance();
        WebDriver driver = BrowserFactory.getDriver(); 

        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
        logger.info("Screenshot saved at: {}", screenshotPath);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: {}", result.getName(), result.getThrowable());
        Object currentClass = result.getInstance();
        WebDriver driver = BrowserFactory.getDriver(); 

        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
        logger.info("Screenshot saved at: {}", screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: {}", result.getName());
        WebDriver driver = BrowserFactory.getDriver();
        String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName());
        logger.info("Screenshot saved at: {}", screenshotPath);
    }
}

