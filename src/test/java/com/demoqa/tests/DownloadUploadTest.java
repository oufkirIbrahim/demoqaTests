package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.elements.DownloadUploadPage;
import com.demoqa.pages.elements.ElementsPage;
import com.demoqa.reports.ExtentReportManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

@Listeners(com.demoqa.listener.TestListener.class)
public class DownloadUploadTest extends BaseTest {

    private String downloadPath;
    private DownloadUploadPage downloadUploadPage;

    @BeforeClass
    @Override
    public void setup() {
        // Setup download directory
        downloadPath = System.getProperty("user.dir") + "/Downloads";
        new File(downloadPath).mkdirs();

        // Configure Chrome options for download
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadPath);
        options.setExperimentalOption("prefs", chromePrefs);

        // Initialize driver with options
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testDownloadFile() {
        // Clean download directory
        cleanDownloadDirectory();

        downloadUploadPage = this.getDownloadUploadPage();
        ExtentReportManager.getTest().info("Navigating to Download/Upload section");

        ExtentReportManager.getTest().info("Clicking download button");
        downloadUploadPage.clickDownloadFile();

        // Wait for download to complete
        delay(3000);

        // Check if file was downloaded
        File downloadDir = new File(downloadPath);
        File[] files = downloadDir.listFiles();

        Assert.assertNotNull(files, "Download directory is empty");
        Assert.assertTrue(files.length > 0, "No files were downloaded");
        ExtentReportManager.getTest().pass("File downloaded successfully: " + files[0].getName());
    }

    private void cleanDownloadDirectory() {
        File directory = new File(downloadPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    private DownloadUploadPage getDownloadUploadPage() {
        ElementsPage elementsPage = homePage.goToElementsPage();
        elementsPage.setLocatorReader("ElementsPage.properties");
        DownloadUploadPage downloadUploadPage = elementsPage.goToDownloadUploadSection();
        downloadUploadPage.setLocatorReader("ElementsPage.properties");
        return downloadUploadPage;
    }

}
