package tests.LoginPageWithDashBoard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;
    private final By logOutBy = By.id("logout");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogout() {
        driver.findElement(logOutBy).click();
    }

    public boolean isLoggedIn() {
        System.out.println("Current URL in DashboardPage: " + driver.getCurrentUrl()); // Debugging line
        return driver.getCurrentUrl().contains("logged-in-successfully/");
    }
}