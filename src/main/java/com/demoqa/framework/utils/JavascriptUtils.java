package com.demoqa.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtils {

    public static void scrollToElement(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        String jscript = "arguments[0].scrollIntoView(true);";
        ((JavascriptExecutor) driver).executeScript(jscript, element);
    }

}
