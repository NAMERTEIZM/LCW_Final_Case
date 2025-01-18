package Tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)
@Epic("Base Configuration")
@Feature("Browser Initialization")
public class BaseTest {

    protected WebDriver driver;

    @BeforeAll
    @Step("Setting up the browser and navigating to the LC Waikiki homepage")
    @Description("This step initializes the WebDriver, sets up the browser with required options, and navigates to the LC Waikiki website.")
    @Severity(SeverityLevel.CRITICAL)
    public void setUp() {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://www.lcw.com/");
            Allure.addAttachment("Browser Launched", "Browser launched and navigated to LC Waikiki homepage.");
            System.out.println("Driver initialized successfully");
        } catch (Exception e) {
            Allure.addAttachment("Setup Error", e.getMessage());
            System.out.println("Driver initialization failed: " + e.getMessage());
        }
    }

    @AfterAll
    @Step("Closing the browser and cleaning up resources")
    @Description("This step closes the WebDriver instance and cleans up any resources used during the tests.")
    @Severity(SeverityLevel.NORMAL)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            Allure.addAttachment("Browser Closed", "The browser instance was closed successfully.");
        }
    }
}
