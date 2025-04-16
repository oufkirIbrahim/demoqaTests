package com.demoqa.base;

import com.demoqa.pages.BasePage;
import com.demoqa.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected   WebDriver driver;
    private ChromeOptions options;
    protected BasePage basePage;
    protected HomePage homePage;
    private String baseUrl = "https://demoqa.com/";

    @BeforeClass
    public void setup(){
        options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void loadApplication(){
        driver.get(baseUrl);
        basePage = new BasePage();
        BasePage.setDriver(driver);
        homePage = new HomePage();
        homePage.setLocatorReader("HomePage.properties");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

}
