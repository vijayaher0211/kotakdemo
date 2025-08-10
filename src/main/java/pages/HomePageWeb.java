package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



/**
 * Handles all UI actions related to the Home page of the web application.
 */

public class HomePageWeb {
	
	WebDriver driver;
	
	@FindBy(xpath = "//a[text()='Log out']")
	WebElement logoutButton;
	
	@FindBy(xpath ="//h1[text()='Logged In Successfully']")
	WebElement loggedInSuccessMessage;
	
	@FindBy(xpath="//h2[text()='Test login']")
	WebElement testLoginText;
	
	public HomePageWeb(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver =driver;
	}
	
	public String loginSuccessMessage() {
		loggedInSuccessMessage.isDisplayed();
		return loggedInSuccessMessage.getText();
	}
	
	public void logout() {
		logoutButton.click();
		testLoginText.isDisplayed();
	}
	
	public String getPageTitle() {
		loggedInSuccessMessage.isDisplayed();
		return driver.getTitle();
	}
	
	
	

}
