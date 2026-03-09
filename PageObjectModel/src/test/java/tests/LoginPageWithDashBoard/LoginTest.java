package tests.LoginPageWithDashBoard;

import LoginPageWithDashBoard.LoginPageWithDashBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import tests.LoginPageWithDashBoard.DashboardPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private WebDriver driver;
    private LoginPageWithDashBoard loginPage;
    private DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPageWithDashBoard(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testdatafordashboard.csv", numLinesToSkip = 1)
    void testLogin(String username, String password, boolean shouldSucceed, String expectedMessage) {
        loginPage.open();

        // Add explicit wait to make sure page loads fully
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));  // Wait for Submit button

        // Attempt to login
        loginPage.login(username, password);

        if (shouldSucceed) {
            wait.until(ExpectedConditions.urlContains("logged-in-successfully/")); // Wait for successful login
            assertTrue(dashboardPage.isLoggedIn());
        } else {
            assertTrue(loginPage.isErrorDisplayed());
            assertEquals(expectedMessage, loginPage.getErrorMessage());
        }
    }
}