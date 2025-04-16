package com.demoqa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extentReports;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static String reportPath; // 🔄 pour stocker le chemin

    public static void initReport() {
        reportPath = "reports/extent-report_" + getDateTime() + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("DemoQA Automation Report");
        reporter.config().setDocumentTitle("Test Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
    }

    private static String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public static void flushReport() {
        extentReports.flush();

        // Copier le fichier vers un nom fixe pour Jenkins
        try {
            Path source = Paths.get(reportPath);
            Path target = Paths.get("reports/ExtentReport.html");
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("ExtentReport copied to reports/ExtentReport.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTest(String testName) {
        extentTest.set(extentReports.createTest(testName));
    }

    public static ExtentTest getTest() {
        return extentTest.get();
    }
}
