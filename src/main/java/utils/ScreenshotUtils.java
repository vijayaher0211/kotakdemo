package utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


/**
 * ScreenshotUtils
 *
 * Utility class for capturing and saving screenshots during test execution.
 * - Captures screenshots with a timestamped filename for uniqueness.
 * - Stores them in the directory defined in configuration (screenshotPath).
 * - Provides helper method to get current date and time in a custom format.
 *
 * Usage: Call captureScreenshot(driver, "StepName") to save a screenshot for a specific test step.
 */


public class ScreenshotUtils {

	static String screenshotPath = ReadConfigFile.get("screenshotPath");
	
	public static String captureScreenshot(WebDriver driver, String stepName){

		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String screenshotDir = System.getProperty("user.dir") +"/" +screenshotPath;
		String screenshotPath = screenshotDir + stepName + "_" + timestamp + ".png";
		
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        File targetFile = new File(screenshotPath);
	        targetFile.getParentFile().mkdirs(); // Ensure folder exists
	        FileUtils.copyFile(src, targetFile);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotPath;
	}

	public static String getCurrentDateTime() {
		DateFormat customformat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date CurrentDate = new Date();
		return customformat.format(CurrentDate);
	}

}
