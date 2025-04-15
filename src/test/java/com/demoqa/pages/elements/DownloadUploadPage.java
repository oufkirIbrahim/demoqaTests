package com.demoqa.pages.elements;

import org.openqa.selenium.By;

public class DownloadUploadPage extends ElementsPage{

    private final By downloadFile;
    private final By uploadFile;

    public DownloadUploadPage() {
        super();
        setLocatorReader("ElementsPage.properties");
        downloadFile = locatorReader.getLocator("downloadFile");
        uploadFile = locatorReader.getLocator("uploadFile");
    }

    public void clickDownloadFile() {
        click(downloadFile);
    }

    public void clickUploadFile() {
        click(uploadFile);
    }


}
