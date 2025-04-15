package com.demoqa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void initReport() {
        String reportPath = "reports/extent-report_" + getDateTime() + ".html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("DemoQA Automation Report");
        reporter.config().setDocumentTitle("Test Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
    }

    private static String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static void flushReport() {
        extentReports.flush();
    }

    public static void createTest(String testName) {
        extentTest.set(extentReports.createTest(testName));
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }

}
