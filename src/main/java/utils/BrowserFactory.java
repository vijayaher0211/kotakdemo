package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.testng.annotations.Parameters;

/**
 * BrowserFactory
 * 
 * Utility class to manage WebDriver instances for different browsers (Chrome,
 * Firefox, Edge) in a thread-safe manner using ThreadLocal.
 * 
 * Responsible for: - Launching browsers based on the specified or configured
 * browser name - Navigating to the base URL with configured timeouts -
 * Providing access to the current WebDriver instance - Utility methods like
 * scrolling and quitting the browser
 * 
 * Integrates with configuration file for browser name, URL, and timeout
 * settings.
 */

public class BrowserFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static final Logger logger = Log.getLogger(BrowserFactory.class);
	static ReadConfigFile rd = new ReadConfigFile();

	public static WebDriver launchBrowser(String browserName) {

		// String browserName = ReadConfigFile.getBrowserName();
	    logger.info("Thread {} launching browser: {}", Thread.currentThread().getId(), browserName);

		try {
			switch (browserName.toLowerCase()) {
			case "chrome":
				driver.set(new ChromeDriver());
				break;

			case "firefox":
				driver.set(new FirefoxDriver());
				break;

			case "edge":
				driver.set(new EdgeDriver());
				break;

			default:
				logger.error("Invalid browser name in config: {}", browserName);
				throw new RuntimeException("Unsupported browser: " + browserName);
			}
		} catch (Exception e) {
			logger.error("Failed to launch browser: {}", e.getMessage(), e);
			throw new RuntimeException("Browser launch failed", e);
		}

		return driver.get();
	}

	public static void navigateToUrl(WebDriver driver) {
		String url = ReadConfigFile.getBaseUrl();
		int implicitlyWait = ReadConfigFile.getImplicitWait();
		int pageLoadTimeout = ReadConfigFile.getPageLoadTimeout();

		logger.info("Navigating to URL: {}", url);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void scrollDown(WebDriver driver, int pixels) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + pixels + ")", "");
	}

	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

}