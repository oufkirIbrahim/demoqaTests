package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.helper.UserTestHelper;
import com.demoqa.model.User;
import com.demoqa.pages.elements.ElementsPage;
import com.demoqa.pages.elements.WebTablesPage;

import com.demoqa.reports.ExtentReportManager;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.demoqa.listener.TestListener.class)
public class WebTablesTest extends BaseTest {

    @Test
    public void testAddNewUser() {
        ExtentReportManager.getTest().info("Navigating to Web Tables Page");
        WebTablesPage webTablesPage = getWebTablesPage();

        webTablesPage.clickAddButton();
        ExtentReportManager.getTest().info("Clicked on Add button");

        User user = UserTestHelper.initUser();
        ExtentReportManager.getTest().info("User initialized: " + user.getEmail());

        webTablesPage.addNewUser(user);
        ExtentReportManager.getTest().info("User submitted in the form");

        boolean isDisplayed = webTablesPage.checkIsUserDisplayed(user.getEmail());
        Assert.assertTrue(isDisplayed, "User was not added successfully");
        ExtentReportManager.getTest().pass("User was added successfully");
    }

    @Test
    public void testModifyAge() {
        String email = "alden@example.com";
        String newAge = "30";

        WebTablesPage webTablesPage = getWebTablesPage();
        webTablesPage.clickEditButton(email);
        webTablesPage.modifyAge(newAge);
        webTablesPage.clickEditButton(email);

        String age = webTablesPage.getAge(email);
        Assert.assertEquals(age, newAge + 1, "Age was not modified correctly");
        ExtentReportManager.getTest().pass("Age modified successfully to: " + age);
    }

    @Test
    public void testDeleteUser() {
        String email = "alden@example.com";

        WebTablesPage webTablesPage = getWebTablesPage();
        webTablesPage.deleteUser(email);

        boolean isUserDeleted = webTablesPage.checkIsUserDisplayed(email);
        Assert.assertFalse(isUserDeleted, "User was not deleted successfully");
        ExtentReportManager.getTest().pass("User deleted successfully");
    }

    private WebTablesPage getWebTablesPage() {
        ElementsPage elementsPage = homePage.goToElementsPage();
        elementsPage.setLocatorReader("ElementsPage.properties");
        WebTablesPage webTablesPage = elementsPage.goToTablesSection();
        webTablesPage.setLocatorReader("ElementsPage.properties");
        return webTablesPage;
    }

}

