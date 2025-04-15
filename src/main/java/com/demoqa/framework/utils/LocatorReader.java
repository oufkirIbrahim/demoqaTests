package com.demoqa.framework.utils;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LocatorReader {

    private final Properties properties = new Properties();

    public LocatorReader(String propertiesFileName) {
        try (FileInputStream input = new FileInputStream("src/test/resources/locators/" + propertiesFileName)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file: " + e.getMessage());
        }
    }

    public By getLocator(String name, String... args) {
        String locator = properties.getProperty(name);
        if (locator == null) {
            throw new RuntimeException("Locator not found: " + name);
        }

        // Replace placeholders (???)
        for (String arg : args) {
            locator = locator.replaceFirst("\\?\\?\\?", arg);
        }

        // Parse locator type and value
        String[] parts = locator.split(":", 2); // Split by ":" instead of "="
        if (parts.length != 2) {
            throw new RuntimeException("Invalid locator format: " + locator);
        }

        String type = parts[0].toLowerCase();
        String value = parts[1];

        switch (type) {
            case "xpath":
                return By.xpath(value);
            case "css":
                return By.cssSelector(value);
            case "id":
                return By.id(value);
            case "name":
                return By.name(value);
            case "class":
                return By.className(value);
            case "tag":
                return By.tagName(value);
            case "linktext":
                return By.linkText(value);
            case "partiallinktext":
                return By.partialLinkText(value);
            default:
                throw new RuntimeException("Unsupported locator type: " + type);
        }
    }
}