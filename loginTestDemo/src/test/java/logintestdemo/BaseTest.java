package logintestdemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest {

    // The WebDriver instance used to interact with the web browser
    // This will be used in the actual test classes to perform browser actions
    protected WebDriver driver;

    // The setup method is annotated with @BeforeTest, meaning it will run before any test methods in the test class
    @BeforeTest
    public void setup() {
        // Initialize the ChromeDriver. This creates an instance of ChromeDriver which will open the Chrome browser.
        driver = new ChromeDriver();

        // Maximizing the browser window to ensure all elements are visible for interaction.
        driver.manage().window().maximize();

        // Set an implicit wait timeout for locating elements.
        // It will wait up to 30 seconds for elements to be found before throwing an exception.
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    // The tearDown method is annotated with @AfterTest, meaning it will run after all test methods in the test class.
    @AfterTest
    public void tearDown() {
        // Close the browser and clean up the WebDriver instance after the tests have finished.
        // This ensures that the browser instance is properly closed and any associated resources are released.
        driver.quit();
    }

}