package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    public static WebDriver driver;
    public BasePage(WebDriver driver)
    {
        if (driver == null) {
            System.out.printf("WebDriver instance cannot be null!");
        }
        this.driver = driver;

    }

    By accountUserTextBy = By.className("dropdown-label");
    By searchBox = By.id("search-form__input-field__search-input");


    public String getAccountUserText()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_1_")));
        return driver.findElement(accountUserTextBy).getText();
    }

    public void Search(String Text)
    {
        driver.findElement(searchBox).sendKeys(Text+ Keys.ENTER);
    }
}
