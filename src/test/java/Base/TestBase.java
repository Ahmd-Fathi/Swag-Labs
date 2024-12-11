package Base;

import Pages.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.annotations.Listeners;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Listeners(Base.Listeners.class)
public class TestBase {

    public static WebDriver driver;
    protected inventoryPage inventoryPageObject;
    protected LoginPage LoginPageObject;
    protected CartPage CartPageObject;
    protected CheckoutOverviewPage CheckoutOverviewPageObject;
    protected InformationCheckOutPage InformationCheckOutPageObject;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void startDriver(@Optional("Chrome") String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        inventoryPageObject = new inventoryPage(driver);
        LoginPageObject = new LoginPage(driver);
        CartPageObject = new CartPage(driver);
        InformationCheckOutPageObject = new InformationCheckOutPage(driver);
        CheckoutOverviewPageObject = new CheckoutOverviewPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void quitDriver() throws InterruptedException {
        driver.quit();
    }

    public void attachScreenshotToAllure(String testName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;

        // Get screenshot as bytes and attach it to Allure
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment(testName + " Screenshot", new ByteArrayInputStream(screenshot)); // Attach to Allure

        // Save screenshot locally in ReportsAndScreenshots folder
        File source = ts.getScreenshotAs(OutputType.FILE);
        String projectPath = System.getProperty("user.dir"); // Project root
        File destination = new File(projectPath + "\\ReportsAndScreenshots\\" + testName + ".png");

        // Ensure the directory exists, if not create it
        if (!destination.getParentFile().exists()) {
            destination.getParentFile().mkdirs();
        }

        // Copy the screenshot to the destination folder
        FileUtils.copyFile(source, destination);
    }


    public List<HashMap<String, String>> GetJasonDataToMap(String filepath) throws IOException {
        // Read JSON to string
        String jsonContent = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);

        // Convert string to HashMap using Jackson DataBind
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }
}
