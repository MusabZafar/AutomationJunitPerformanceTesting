package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LoginTest {

    private static final Logger logger = Logger.getLogger(LoginTest.class.getName());

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    static void configureLogging() {
        try (InputStream inputStream =
                     LoginTest.class.getClassLoader().getResourceAsStream("logging.properties")) {
            if (inputStream != null) {
                LogManager.getLogManager().readConfiguration(inputStream);
            }
        } catch (Exception e) {
            System.out.println("Could not load logging.properties: " + e.getMessage());
        }
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        logger.info("Browser launched");

        driver.get("https://practicetestautomation.com/practice-test-login/");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "[{index}] username={0}, expectedSuccess={2}")
    @CsvFileSource(resources = "/testdata.csv", numLinesToSkip = 1)
    void testLogin(String username, String password, boolean shouldSucceed) {
        logger.log(Level.INFO, "START: Testing username={0}", username);

        try {
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickSubmit();

            String url = loginPage.getCurrentUrl();

            if (shouldSucceed) {
                Assertions.assertTrue(url.contains("logged-in-successfully/"));
            } else {
                Assertions.assertTrue(url.contains("practice-test-login/"));
            }

            takeScreenshot("PASS_" + username);
            logger.log(Level.INFO, "SUCCESS: username={0}", username);

        } catch (AssertionError e) {
            takeScreenshot("FAIL_" + username);
            logger.log(Level.WARNING, "FAILED: username={0}", username);
            throw e;
        }
    }

    private void takeScreenshot(String testName) {
        try {
            Files.createDirectories(Paths.get("src/test/resources/screenshots"));

            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().toString().replace(":", "_");
            Path dest = Paths.get("src/test/resources/screenshots/" + testName + "_" + timestamp + ".png");

            Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Screenshot failed", e);
        }
    }
}