package logintestdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    // The WebDriver instance used to interact with the web browser
    private WebDriver driver;

    // Constructor to initialize the WebDriver. This is used to interact with the browser.
    public LoginPage(WebDriver driver) {
        this.driver = driver; // Assigning the WebDriver to the instance variable
    }

    // Method to locate the email address input field by its ID.
    // This is used to interact with the email field in the login form.
    private WebElement emailAddressField() {
        return driver.findElement(By.id("input-email")); // Using the ID selector to locate the email input
    }

    // Method to locate the password input field by its ID.
    // This is used to interact with the password field in the login form.
    private WebElement passwordField() {
        return driver.findElement(By.id("input-password")); // Using the ID selector to locate the password input
    }

    // Method to locate the login button by its CSS selector.
    // This button is clicked to submit the login form.
    private WebElement loginBtn() {
        return driver.findElement(By.cssSelector("input.btn")); // Using the CSS selector to locate the login button
    }

    // Method to perform the login operation by providing email and password as parameters.
    // This will clear the existing values in the fields (if any), input the new values, and click the login button.
    public void performLogin(String email, String password){
        emailAddressField().clear(); // Clear any pre-filled text in the email field
        emailAddressField().sendKeys(email); // Enter the email into the email field
        passwordField().clear(); // Clear any pre-filled text in the password field
        passwordField().sendKeys(password); // Enter the password into the password field
        loginBtn().click(); // Click the login button to submit the form
    }

    // Method to get the error message text that appears when login fails.
    // The error message is located using its CSS selector and is returned as a string.
    public String getErrorMessageText() {
        return driver.findElement(By.cssSelector("#account-login div.alert")).getText();
        // Locating the error message element and retrieving its text to display the error
    }
}