package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BrowserFactory;


/**
 * Handles all UI actions related to the login functionality of the web application.
 */

public class LoginPageWeb {

    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement usernameEntryField;

    @FindBy(id = "password")
    private WebElement passwordEntryField;

    @FindBy(xpath = "//h1[text()='Logged In Successfully']")
    private WebElement loggedInSuccessMessage;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(id = "error")
    private WebElement errorMessage;

    @FindBy(xpath = "//h2[text()='Test login']")
    private WebElement testLoginText;

    public LoginPageWeb(WebDriver driver) {
    	PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return BrowserFactory.getDriver().getTitle();
    }

    public void enterUsername(String username) {
        usernameEntryField.clear();
        usernameEntryField.sendKeys(username);
    }

    public void enterPassword(String password) {
        passwordEntryField.clear();
        passwordEntryField.sendKeys(password);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public String loginSuccessMessage() {
        return loggedInSuccessMessage.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
