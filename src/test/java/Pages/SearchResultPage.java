package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage extends BasePage {

    By searchResultText = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[3]/div[1]/div/h1");

    public SearchResultPage (WebDriver driver)
    {
        super(driver);
    }

    public String searchTerm()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[5]/button")));
        return driver.findElement(searchResultText).getText();
    }


}
