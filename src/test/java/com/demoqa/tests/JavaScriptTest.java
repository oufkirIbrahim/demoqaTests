package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import org.testng.annotations.Test;

public class JavaScriptTest extends BaseTest {

    @Test
    public void testScrollingToElement(){
        System.out.println(">>> Start testScrollingToElement");
        homePage.goToElementsPage();
        System.out.println(">>> Finished goToElementsPage");
    }

}
