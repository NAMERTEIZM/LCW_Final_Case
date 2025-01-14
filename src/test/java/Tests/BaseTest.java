package Tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.MethodName.class)

public class BaseTest {

   protected WebDriver driver;

    @BeforeAll
    public void setUp()
    {
        try {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get("https://www.lcw.com/");
            System.out.println("Driver initialized successfully");
        } catch (Exception e) {
            System.out.println("Driver initialization failed: " + e.getMessage());
        }
    }

    @AfterAll
    public void tearDown()
    {
        driver.quit();
    }


}
