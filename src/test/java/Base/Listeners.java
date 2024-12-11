package Base;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Listeners extends TestBase implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        logTestStatus("Test started: ", result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logTestStatus("Test passed: ", result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logTestStatus("Test failed: ", result);

        // Capture and attach screenshot only if driver is not null
        if (driver != null) {
            try {
                // Call the method from TestBase to capture and attach the screenshot to Allure
                attachScreenshotToAllure(result.getMethod().getMethodName(), driver);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to capture screenshot for: " + result.getMethod().getMethodName());
            }
        } else {
            System.out.println("WebDriver is null, unable to capture screenshot.");
        }

        // Log failure reason to Allure report
        if (result.getThrowable() != null) {
            Allure.addAttachment("Failure Reason", result.getThrowable().toString());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logTestStatus("Test skipped: ", result);
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("All tests finished.");
    }

    // Utility method for logging and Allure attachment
    private void logTestStatus(String status, ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println(status + methodName);
        Allure.addAttachment(status, methodName + " " + status.toLowerCase());
    }

    // Method to capture screenshot and attach to Allure
    private void attachScreenshot(String testName) {
        try {
            String screenshotPath = captureScreenshot(testName);
            Allure.addAttachment(testName + " Screenshot", "image/png", new FileInputStream(screenshotPath), "png");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to capture screenshot for: " + testName);
        }
    }

    // Captures and saves the screenshot
    private String captureScreenshot(String testName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/ReportsAndScreenshots/" + testName + ".png";
        FileUtils.copyFile(screenshot, new File(screenshotPath));
        return screenshotPath;
    }
}
