package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Utility class for taking screenshots during test execution.
 * Screenshots provide evidence for the assessment and help with debugging.
 */
public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String fileName) {
        if (driver == null) {
            return;
        }

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destination = new File("screenshots/" + fileName + ".png");

            // Create screenshots folder if it does not already exist
            if (destination.getParentFile() != null) {
                destination.getParentFile().mkdirs();
            }

            Files.copy(srcFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved to: " + destination.getAbsolutePath());

        } catch (IOException e) {
            System.out.println("Screenshot could not be saved: " + e.getMessage());
        }
    }
}