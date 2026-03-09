package logintestdemo;


import logintestdemo.pages.LoginPage;
import logintestdemo.pages.MyAccountPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class LoginPageTest extends BaseTest {

    // DataProvider to supply test data (email, password, expected validity)
    @DataProvider
    public Iterator<Object[]> getLoginData() {
        // Create a list of login data to test multiple scenarios
        List<Object[]> loginData = new ArrayList<>();

        // Adding valid and invalid test cases
        loginData.add(new Object[]{"mojtaba@gmail.com", "Password", false});  // Invalid password
        loginData.add(new Object[]{"mojtaba@gmail.com", "", false});  // Empty password field
        loginData.add(new Object[]{"mojtaba@gmail.com", "raufdev", true});  // Valid login credentials

        // Returning the iterator of login data for the test method
        return loginData.iterator();
    }

    // Test method that runs for each set of data provided by the DataProvider
    @Test(dataProvider = "getLoginData")
    public void testLoginFeature(String email, String password, boolean isValidUser) {
        // Navigate to the login page
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");

        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(driver);

        // Perform login with the provided email and password
        loginPage.performLogin(email, password);

        // Check if the login attempt is valid or invalid based on the test data
        if (!isValidUser) {
            // Assert that the error message is correct for invalid credentials
            assertEquals(loginPage.getErrorMessageText(), "Warning: No match for E-Mail Address and/or Password.");
        } else {
            // Create an instance of MyAccountPage to verify login success
            MyAccountPage myAccountPage = new MyAccountPage(driver);

            // Assert that the page title on the My Account page is correct, indicating successful login
            assertEquals(myAccountPage.getPageTitle(), "My Account");
        }
    }
}