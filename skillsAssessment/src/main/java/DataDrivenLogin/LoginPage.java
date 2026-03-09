package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

/**
 * Page Object Model for the SauceDemo login page.
 * This class stores the page locators and page actions.
 * Keeping locators here means the test class stays clean and focused on test logic.
 */
public class LoginPage {

    private WebDriver driver;

    // TODO – Locators
    // SauceDemo login page elements
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");

    // This locator is used to read the error shown after a failed login attempt
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    // TODO – Constructor
    // The constructor receives the browser driver from the test class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // TODO – Actions
    // Enter username into the username input field
    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    // Enter password into the password input field
    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    // Click the login button
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Reusable method that performs a full login
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Read the error message if it is displayed
    // If the error is not found, return empty text rather than crashing the test
    public String getErrorMessage() {
        try {
            return driver.findElement(errorMessage).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}