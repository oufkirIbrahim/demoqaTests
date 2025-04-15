package com.demoqa.pages.elements;

import com.demoqa.pages.HomePage;
import org.openqa.selenium.By;

import static com.demoqa.base.BaseTest.delay;

public class ElementsPage extends HomePage {

    public ElementsPage() {
        super();
    }

    public WebTablesPage goToTablesSection(){
        By tablesSection = locatorReader.getLocator("webTables");
        click(tablesSection);
        return new WebTablesPage();
    }

    public LinksPage goToLinksSection(){
        By linksSection = locatorReader.getLocator("links");
        click(linksSection);
        return new LinksPage();
    }

    public DownloadUploadPage goToDownloadUploadSection() {
        By downloadUploadSection = locatorReader.getLocator("downloadAndUpload");
        click(downloadUploadSection);
        delay(2000);
        return new DownloadUploadPage();
    }

}