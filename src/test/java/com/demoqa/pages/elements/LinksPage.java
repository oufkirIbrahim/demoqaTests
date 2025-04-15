package com.demoqa.pages.elements;

import com.demoqa.framework.utils.JavascriptUtils;
import org.openqa.selenium.By;

import static com.demoqa.base.BaseTest.delay;

public class LinksPage extends ElementsPage {

    private final By badRequestLink;
    private final By responseMessage;

    public LinksPage(){
        super();
        setLocatorReader("ElementsPage.properties");
        badRequestLink = locatorReader.getLocator("badRequestLink");
        responseMessage = locatorReader.getLocator("responseMessage");
    }

    public void clickBadRequestLink() {
        JavascriptUtils.scrollToElement(driver, badRequestLink);
        click(badRequestLink);
    }

    public String getResponse(){
        delay(2000);
        return getText(responseMessage);
    }
}
