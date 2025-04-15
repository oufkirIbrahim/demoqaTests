package com.demoqa.framework.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.err.println("Driver is null, cannot capture screenshot");
            return null;
        }

        // Create screenshots directory if it doesn't exist
        File directory = new File("reports/screenshots");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Use timestamp in filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";

        // Full path for saving the file
        String filePath = "reports/screenshots/" + fileName;

        try {
            // Take screenshot
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(source.toPath(), Paths.get(filePath),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);

            // Return relative path for report
            return "screenshots/" + fileName;
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}