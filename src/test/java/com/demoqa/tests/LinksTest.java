package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.elements.ElementsPage;
import com.demoqa.pages.elements.LinksPage;
import com.demoqa.reports.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.demoqa.listener.TestListener.class)
public class LinksTest extends BaseTest {

    @Test
    public void testValidLink() {
        LinksPage linksPage = getLinksPage();
        ExtentReportManager.getTest().info("Navigating to Links Page");
        linksPage.clickBadRequestLink();
        ExtentReportManager.getTest().info("Clicked on Bad Request link");
        String message = linksPage.getResponse();
        ExtentReportManager.getTest().info("Response message: " + message);
        Assert.assertTrue( message.contains("400") && message.contains("Bad Request"), "Response message is incorrect");
    }

    private LinksPage getLinksPage() {
        ElementsPage elementsPage = homePage.goToElementsPage();
        elementsPage.setLocatorReader("ElementsPage.properties");
        LinksPage linksPage = elementsPage.goToLinksSection();
        return linksPage;
    }
}
