package LoginPageWithDashBoard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageWithDashBoard {
    private WebDriver driver;

    private final By usernameBy = By.id("username");
    private final By passwordBy = By.id("password");
    private final By submitBy = By.id("submit");
    private final By errorBy = By.id("error");

    public LoginPageWithDashBoard(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    public void enterUsername(String username) {
        driver.findElement(usernameBy).clear();
        driver.findElement(usernameBy).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordBy).clear();
        driver.findElement(passwordBy).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(submitBy).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();

    }

    public boolean isErrorDisplayed() {
        return driver.findElement(errorBy).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorBy).getText();
    }

    // Debugging: Add a method to print the current URL after login
    public boolean isLoggedIn() {
        System.out.println("Current URL after login: " + driver.getCurrentUrl()); // Debugging line
        return driver.getCurrentUrl().contains("logged-in-successfully/");
    }
}