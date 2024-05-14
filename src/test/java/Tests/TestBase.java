package Tests;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.nio.file.Files;

public abstract class TestBase {
    protected WebDriver driver;

    public WebDriver getDriver(){
        return driver;
    }

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",  "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (!result.isSuccess()) {
            try {

                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                File screenshotsDir = new File("screenshots");
                if (!screenshotsDir.exists()) {
                    screenshotsDir.mkdirs();
                }

                File destFile = new File(screenshotsDir, result.getName() + ".png");

                Files.copy(scrFile.toPath(), destFile.toPath());
                System.out.println("Screenshot taken: " + destFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error while taking screenshot: " + e.getMessage());
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
