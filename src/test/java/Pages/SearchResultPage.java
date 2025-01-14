package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultPage extends BasePage {

    private By searchResultText = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[3]/div[1]/div/h1");
    private final By TabHoverButtonLocator = By.xpath("//*[@id=\"header__container\"]/header/div[3]/nav/ul/li[4]/a");
    private final By CategoryHoverButtonLocator = By.xpath("//*[@id=\"header__container\"]/header/div[3]/nav/ul/li[4]/div/nav/ul/button[4]/span[1]");
    private final By ProductCategoryButton = By.xpath("//*[@id=\"header__container\"]/header/div[2]/nav/ul/li[4]/div/nav/section/div[1]/div[2]/ul/li[5]/a");
    private String URL = "https://www.lcw.com/kiz-cocuk-dis-giyim-t-1010";

    // Checkbox XPath tanımları
    private final By checkbox1Locator = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[4]/div/div[2]/div[3]/div[2]/div/span");
    private final By checkbox2Locator = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[4]/div/div[2]/div[3]/div[3]/div/span");
    private final By checkbox3Locator = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[4]/div/div[2]/div[3]/div[4]/div/span");
    private final By colourLocator = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[5]/div/div[2]/div[3]/div[2]/span[1]/img");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String searchTerm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[5]/button")));
        return driver.findElement(searchResultText).getText();
    }

    public void HoverAndClickForSearch() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // İlk hover işlemi
            WebElement hoverTab = wait.until(ExpectedConditions.visibilityOfElementLocated(TabHoverButtonLocator));
            actions.moveToElement(hoverTab).perform();

            // İkinci hover işlemi
            WebElement categoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(CategoryHoverButtonLocator));
            actions.moveToElement(categoryTab).perform();
            driver.navigate().to(URL);

            // "Mont ve Kaban" elementini bul ve tıkla
            WebElement productCategory = wait.until(ExpectedConditions.elementToBeClickable(ProductCategoryButton));
            productCategory.click();



            // Checkbox işlemleri
            scrollToElementAndClick(js, checkbox1Locator, wait);
            Thread.sleep(5000);

            scrollToElementAndClick(js, checkbox2Locator, wait);
            Thread.sleep(5000);

            scrollToElementAndClick(js, checkbox3Locator, wait);
            Thread.sleep(5000);

            scrollToElementAndClick(js, colourLocator, wait);
            Thread.sleep(5000);

            System.out.println("Başarıyla tüm işlemler tamamlandı.");
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void scrollToElementAndClick(JavascriptExecutor js, By elementLocator, WebDriverWait wait) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception e) {
            System.out.println("Elemente tıklama hatası: " + e.getMessage());
        }
    }
}
