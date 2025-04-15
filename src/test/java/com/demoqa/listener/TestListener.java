package com.demoqa.listener;

import com.aventstack.extentreports.Status;
import com.demoqa.base.BaseTest;
import com.demoqa.framework.utils.ScreenshotUtil;
import com.demoqa.reports.ExtentReportManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        ExtentReportManager.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReportManager.getTest().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
            String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());

            if (ExtentReportManager.getTest() != null && screenshotPath != null) {
                try {
                    ExtentReportManager.getTest()
                            .fail("Test Failed: " + result.getMethod().getMethodName())
                            .addScreenCaptureFromPath(screenshotPath);
                    ExtentReportManager.getTest().fail(result.getThrowable());
                } catch (Exception e) {
                    ExtentReportManager.getTest().fail("Failed to attach screenshot: " + e.getMessage());
                    ExtentReportManager.getTest().fail(result.getThrowable());
                }
            }
        } catch (Exception e) {
            System.err.println("Error in onTestFailure: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReportManager.getTest().skip("Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
    }
}
