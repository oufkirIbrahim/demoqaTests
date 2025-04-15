package com.demoqa.pages;

import com.demoqa.framework.utils.LocatorReader;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    public static WebDriver driver;
    public LocatorReader locatorReader;

    public BasePage() {

    }

    public BasePage(LocatorReader locatorReader) {
        this.locatorReader = locatorReader;
    }

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    public void setLocatorReader(String locatorFile) {
        locatorReader = new LocatorReader(locatorFile);
    }

    protected WebElement find(By by) {
        return driver.findElement(by);
    }

    protected String getText(By by) {
        return find(by).getText();
    }

    protected String getValue(By by) {
        return find(by).getDomAttribute("value");
    }

    protected void setText(By by, String text) {
        WebElement element = find(by);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By by) {
        find(by).click();
    }

    protected boolean isElementPresent(By by) {
        try {
            find(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
