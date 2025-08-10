package web;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.HomePageWeb;
import pages.LoginPageWeb;
import utils.BrowserFactory;
import utils.GetTestData;



/**
 * WebSampleTest
 *
 * Demonstrates a sample web login test using TestNG with DataProvider.
 * Executes login scenarios (both valid and invalid) based on external test data.
 * Uses assertions for validation and logs steps for reporting.
 * Grouped under 'Sanity' for targeted execution.
 */


public class WebSampleTest extends BaseClass{

	
	@DataProvider()
	public Object[][] getTestData() {
		Object data[][] = GetTestData.getTestData("LoginTestData");
		return data;	
	} 

	@Test(groups = {"Sanity"},description = "Test Login Functionality", dataProvider="getTestData", retryAnalyzer = utils.RetryAnalyzer.class)
	public void loginTest(String username, String password, String expectedMessage) throws InterruptedException, IOException  {

	    LoginPageWeb loginWeb = new LoginPageWeb(BrowserFactory.getDriver());

	    // Verify page title
	    Assert.assertEquals(loginWeb.getPageTitle(), "Test Login | Practice Test Automation", 
	            "Login page title mismatch");
	    logStep("Verified login page title");

	    // Enter credentials step-by-step for screenshot logging
	    loginWeb.enterUsername(username);
	    logStep("Entered username" );

	    loginWeb.enterPassword(password);
	    logStep("Entered password");

	    loginWeb.clickSubmit();
	    logStep("Clicked Submit Button");

	    // Verify login outcome
	    if (expectedMessage.contains("Successfully")) {
	        HomePageWeb homePage = new HomePageWeb(BrowserFactory.getDriver());
	        
	        // Verify Login Success Message
	        Assert.assertEquals(homePage.loginSuccessMessage(),expectedMessage ,"Home page title after login is incorrect- ");

	        logStep("Logged in  Successful");

	        // Logout 
	        homePage.logout();
	        logStep("Logged out successfully");

	    } else {
	    	BrowserFactory.scrollDown(BrowserFactory.getDriver(), 250);
	        
	        String actualErrorMessage = loginWeb.getErrorMessage(); 
	        Assert.assertEquals(actualErrorMessage,expectedMessage ,"Expected Error message is not matching - ");

	        logStep("Error message displayed for invalid login");
	    }
	}



}
