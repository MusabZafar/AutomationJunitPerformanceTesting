

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.ScreenshotUtil;

import java.time.Duration;
import java.util.logging.Logger;

/**
 * Data-driven login test for SauceDemo.
 * Uses JUnit 5 parameterized tests with CSV input data.
 */
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    private static final Logger logger = Logger.getLogger(LoginTest.class.getName());

    // SauceDemo login page
    private final String LOGIN_URL = "https://www.saucedemo.com/";
    private final String SUCCESS_URL = "https://www.saucedemo.com/inventory.html";

    // TODO – @BeforeEach Code
    // Runs before each test case to start a clean browser session
    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
    }

    // TODO – @ParametrizedTest Code
    // Reads one row at a time from the CSV file and runs the same login test repeatedly
    @ParameterizedTest
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    public void testLogin(String testCaseID, String username, String password, String expectedOutcome) {

        logger.info("Starting test case: " + testCaseID);
        logger.info("Username = " + username + ", Expected = " + expectedOutcome);

        try {
            // Use the Page Object methods to perform login
            loginPage.login(username, password);

            if (expectedOutcome.equalsIgnoreCase("Success")) {
                // Explicit wait: wait until browser navigates to inventory page
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(d -> d.getCurrentUrl().equals(SUCCESS_URL));

                Assertions.assertEquals(SUCCESS_URL, driver.getCurrentUrl(),
                        "Expected successful login, but user was not taken to inventory page.");

                logger.info("Test case " + testCaseID + " PASSED");
            } else if (expectedOutcome.equalsIgnoreCase("Failure")) {
                // For failed logins, check either:
                // 1) user stayed on login page, or
                // 2) an error message is shown
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(d ->
                        d.getCurrentUrl().equals(LOGIN_URL) ||
                                !loginPage.getErrorMessage().isEmpty()
                );

                boolean stayedOnLoginPage = driver.getCurrentUrl().equals(LOGIN_URL);
                boolean errorDisplayed = !loginPage.getErrorMessage().isEmpty();

                Assertions.assertTrue(stayedOnLoginPage || errorDisplayed,
                        "Expected failed login, but neither an error appeared nor did the page remain on login.");

                logger.info("Test case " + testCaseID + " PASSED");
            } else {
                Assertions.fail("Invalid ExpectedOutcome value in CSV: " + expectedOutcome);
            }

        } catch (TimeoutException e) {
            logger.severe("Timeout in test case " + testCaseID + ": " + e.getMessage());
            ScreenshotUtil.captureScreenshot(driver, "TestCase_" + testCaseID + "_Timeout");
            Assertions.fail("TimeoutException occurred: " + e.getMessage());

        } catch (NoSuchElementException e) {
            logger.severe("Element not found in test case " + testCaseID + ": " + e.getMessage());
            ScreenshotUtil.captureScreenshot(driver, "TestCase_" + testCaseID + "_NoSuchElement");
            Assertions.fail("NoSuchElementException occurred: " + e.getMessage());

        } catch (Exception e) {
            logger.severe("Unexpected error in test case " + testCaseID + ": " + e.getMessage());
            ScreenshotUtil.captureScreenshot(driver, "TestCase_" + testCaseID + "_Error");
            Assertions.fail("Unexpected exception occurred: " + e.getMessage());

        } finally {
            // Always capture a screenshot for evidence
            ScreenshotUtil.captureScreenshot(driver, "TestCase_" + testCaseID);

            logger.info("Finished test case: " + testCaseID);
        }
    }

    // TODO – @AfterEach Code
    // Runs after each test case to close the browser
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}