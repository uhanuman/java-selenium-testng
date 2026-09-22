package com.demo.prep.observer;

import com.demo.prep.factory.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestListener implements ITestListener {

    private static final Path SCREENSHOT_DIR = Paths.get("target", "screenshots");

    @Override
    public void onTestStart(ITestResult result) {
        System.out.printf("[TEST] Starting %s.%s%n",
                result.getTestClass().getName(), result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.printf("[TEST] Passed %s.%s%n",
                result.getTestClass().getName(), result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.printf("[TEST] Failed %s.%s%n",
                result.getTestClass().getName(), result.getMethod().getMethodName());
        try {
            WebDriver driver = DriverFactory.getThreadDriver();
            if (driver != null) {
                System.out.printf("[TEST] Failure context url=%s title=%s%n",
                        driver.getCurrentUrl(), driver.getTitle());
            }
            if (driver instanceof TakesScreenshot) {
                captureScreenshot(result.getMethod().getMethodName(), (TakesScreenshot) driver);
            }
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.printf("[SUITE] Finished %s with %d passed, %d failed, %d skipped%n",
                context.getName(),
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
    }

    private void captureScreenshot(String testName, TakesScreenshot screenshotTaker) throws IOException {
        Files.createDirectories(SCREENSHOT_DIR);
        File screenshot = screenshotTaker.getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(),
                SCREENSHOT_DIR.resolve(testName + "_" + System.currentTimeMillis() + ".png"),
                StandardCopyOption.REPLACE_EXISTING);
    }
}