package com.demoqa.pages;

import com.demoqa.framework.utils.JavascriptUtils;
import com.demoqa.pages.elements.ElementsPage;
import org.openqa.selenium.By;

public class HomePage extends BasePage{

    public FormsPage goToFormsPage() {
        By formsCard = locatorReader.getLocator("formsCard");
        JavascriptUtils.scrollToElement(driver, formsCard);
        click(formsCard);
        return new FormsPage();
    }

    public ElementsPage goToElementsPage() {
        By elementsCard = locatorReader.getLocator("elementsCard");
        JavascriptUtils.scrollToElement(driver, elementsCard);
        click(elementsCard);
        return new ElementsPage();
    }

}
