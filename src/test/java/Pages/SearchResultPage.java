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
    private final By ProductCategoryButton = By.cssSelector("a.zone-item__anchor[href=\"/kiz-cocuk-dis-giyim-t-1010\"]\n");
    private final By scrollableDivLocator = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]");
    private final By checkbox1Locator = By.cssSelector("span.filter-option__text:contains(\"5-6 Yaş\")\n");
    private final By checkbox2Locator = By.xpath("//span[contains(@class, 'filter-option__text') and contains(text(), '6 Yaş')]");
    private final By checkbox3Locator = By.xpath("//span[contains(@class, 'filter-option__text') and contains(text(), '6-7 Yaş')]");
    private final By colourLocator = By.xpath("//div[contains(@class, 'color-filter-option')]//span[contains(@class, 'color-filter-option__text') and text()='BEJ']");
    private final By cookieAcceptButton = By.id("cookieseal-banner-accept");

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

        try {
            acceptCookies(); // Çerez bannerını kapatma metodu çağrıldı

            // İlk hover işlemi
            WebElement hoverTab = wait.until(ExpectedConditions.visibilityOfElementLocated(TabHoverButtonLocator));
            actions.moveToElement(hoverTab).perform();

            // İkinci hover işlemi
            WebElement categoryTab = wait.until(ExpectedConditions.visibilityOfElementLocated(CategoryHoverButtonLocator));
            actions.moveToElement(categoryTab).perform();

            // "Mont ve Kaban" elementini bul ve tıkla
            WebElement productCategory = wait.until(ExpectedConditions.elementToBeClickable(ProductCategoryButton));
            productCategory.click();
            waitForPageToLoad();

            // Filtreleme işlemleri

            applyFilter(scrollableDivLocator, checkbox1Locator, wait); // 5-6 Yaş
            applyFilter(scrollableDivLocator, checkbox2Locator, wait); // 6 Yaş
            applyFilter(scrollableDivLocator, checkbox3Locator, wait); // 6-7 Yaş
            applyFilter(scrollableDivLocator, colourLocator, wait);   // BEJ

            System.out.println("Başarıyla tüm işlemler tamamlandı.");
            Thread.sleep(20000); // Sayfanın kullanıcı tarafından incelenmesi için 20 saniye bekle
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void applyFilter(By divLocator, By checkboxLocator, WebDriverWait wait) {
        try {
            WebElement scrollableDiv = wait.until(ExpectedConditions.presenceOfElementLocated(divLocator));
            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));

            // Checkbox görünmüyorsa div içinde kaydır
            while (!checkbox.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[1];", scrollableDiv, 200);
                Thread.sleep(500);
            }

            // Checkbox görünürse ve seçili değilse tıkla
            if (!checkbox.isSelected()) {
                checkbox.click(); // Checkbox'a tıkla
                System.out.println("Başarıyla tıklandı: " + checkboxLocator.toString());

                // Tıklamadan sonra sayfanın tamamen yüklenmesini bekle
                waitForPageToLoad();
                Thread.sleep(1000); // Ekstra bekleme süresi
            } else {
                System.out.println("Checkbox zaten seçili: " + checkboxLocator.toString());
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("Element değişmiş: " + checkboxLocator.toString() + " - Yeniden denenecek.");
            applyFilter(divLocator, checkboxLocator, wait); // Element yeniden kontrol edilir
        } catch (Exception e) {
            System.out.println("Filtreleme sırasında hata: " + checkboxLocator.toString() + " - " + e.getMessage());
        }
    }


    private void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Sayfa yükleme sırasında hata: " + e.getMessage());
        }
    }

    private void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            cookieButton.click();
            System.out.println("Çerezler başarıyla kabul edildi.");
        } catch (Exception e) {
            System.out.println("Çerez bannerını kapatma sırasında hata: " + e.getMessage());
        }
    }
}
