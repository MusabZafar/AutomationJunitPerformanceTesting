package logintestdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    // The WebDriver instance that is used to interact with the web browser
    private final WebDriver driver;

    // Constructor to initialize the WebDriver
    // This constructor is called when an instance of MyAccountPage is created and is used to interact with the page.
    public MyAccountPage(final WebDriver driver) {
        this.driver = driver; // Assign the WebDriver instance to the class's driver field
    }

    // Method to retrieve the title text of the My Account page
    // This method finds the h2 element inside the #content section and retrieves its text.
    // It typically returns the title or heading of the page that identifies the current section or page.
    public String getPageTitle() {
        return driver.findElement(By.cssSelector("#content h2")).getText();
        // Using the CSS selector "#content h2" to locate the <h2> element inside the <div id="content"> and fetch its text
    }
}