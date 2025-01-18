package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultPage extends BasePage {

    private By searchResultText = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[3]/div[1]/div/h1");
    private final By TabHoverButtonLocator = By.xpath("//*[@id=\"header__container\"]/header/div[3]/nav/ul/li[4]/a");
    private final By CategoryHoverButtonLocator = By.xpath("//*[@id=\"header__container\"]/header/div[3]/nav/ul/li[4]/div/nav/ul/button[4]/span[1]");
    private final By ProductCategoryButton = By.cssSelector("a.zone-item__anchor[href=\"/kiz-cocuk-dis-giyim-t-1010\"]\n");
    private final By scrollableDivLocator = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]");
    private final By checkbox1Locator = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[6]/div/div[1]/div/div[4]/div/div[2]/div[3]/div[2]/div/span");
    private final By checkbox2Locator = By.xpath("//div[contains(@class, 'filter-option') and .//span[text()='6 Yaş']]//span[@class='lcw-checkbox__checkbox']");
    private final By checkbox3Locator = By.xpath("//span[contains(@class, 'filter-option__text') and contains(text(), '6-7 Yaş')]");
    private final By colourLocator = By.xpath("//div[contains(@class, 'color-filter-option')]//span[contains(@class, 'color-filter-option__text') and text()='BEJ']");
    private final By cookieAcceptButton = By.id("cookieseal-banner-accept");
    private final By dropdownButton = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[5]/div[1]/div/div");
    private final By bestSeller = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[5]/div[1]/div/div/div/a[5]");
    private final By productList = By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[8]/div/div/div");

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
            driver.navigate().to("https://www.lcw.com/kiz-cocuk-dis-giyim-t-1010?beden=5-6-yas,6-yas,6-7-yas&renk=bej");

            // Dropdown ve Best Seller'a tıklama
            WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
            dropdown.click();
            Thread.sleep(2000);

            WebElement bestSellerOption = wait.until(ExpectedConditions.elementToBeClickable(bestSeller));
            bestSellerOption.click();
            waitForPageToLoad();

            // Ürün listesinden 4. ürünü seç ve tıkla
            selectFourthProduct(driver,wait);

            System.out.println("Başarıyla tüm işlemler tamamlandı.");
        } catch (Exception e) {
            System.out.println("Hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void applyFilter(By divLocator, By checkboxLocator, WebDriverWait wait) {
        try {
            scrollToElementInsideDiv(divLocator, checkboxLocator, wait);

            WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));
            if (!checkbox.isSelected()) {
                checkbox.click(); // Checkbox'a tıkla
                System.out.println("Checkbox başarıyla tıklandı: " + checkboxLocator.toString());
                waitForPageToLoad();
            } else {
                System.out.println("Checkbox zaten seçili: " + checkboxLocator.toString());
            }
        } catch (Exception e) {
            System.out.println("Filtreleme sırasında hata: " + checkboxLocator.toString() + " - " + e.getMessage());
        }
    }

    private void scrollToElementInsideDiv(By divLocator, By elementLocator, WebDriverWait wait) {
        WebElement scrollableDiv = wait.until(ExpectedConditions.presenceOfElementLocated(divLocator));
        WebElement targetElement;

        int maxScrollAttempts = 10; // Maksimum scroll deneme sayısı
        int currentAttempt = 0;
        boolean isElementVisible = false;

        while (currentAttempt < maxScrollAttempts) {
            try {
                targetElement = wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));

                // Eğer element görünürse işlemi sonlandır
                if (targetElement.isDisplayed()) {
                    isElementVisible = true;
                    break;
                }

                // Element görünmüyorsa scroll yap
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollTop = arguments[0].scrollTop + arguments[1];",
                        scrollableDiv, 100); // 100 piksel aşağı kaydır
                Thread.sleep(500);

            } catch (Exception e) {
                System.out.println("Scroll sırasında hata: " + e.getMessage());
            }

            currentAttempt++;
        }

        if (!isElementVisible) {
            throw new NoSuchElementException("Element görünür hale getirilemedi: " + elementLocator.toString());
        }
    }

    private void selectFourthProduct(WebDriver driver, WebDriverWait wait) {
        try {
            // Ürünlerin yer aldığı locator
            By productListLocator = By.cssSelector(".product-card.product-card--one-of-4");

            // Ürünlerin yüklenmesini bekle
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productListLocator));

            // Ürün listesini al
            List<WebElement> products = driver.findElements(productListLocator);

            // 4. ürünün mevcut olup olmadığını kontrol et
            if (products.size() < 4) {
                throw new NoSuchElementException("Ürün listesinde 4. ürün bulunamadı. Mevcut ürün sayısı: " + products.size());
            }

            // 4. ürünü al
            WebElement fourthProduct = products.get(2); // List indeksleri 0'dan başlar

            // Ürünü görüntü alanına kaydır ve tıkla
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fourthProduct);
            wait.until(ExpectedConditions.elementToBeClickable(fourthProduct)).click();
            Thread.sleep(10000);

            System.out.println("4. ürüne başarıyla tıklandı.");
        } catch (Exception e) {
            System.out.println("4. ürüne tıklama sırasında hata: " + e.getMessage());
            e.printStackTrace();
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
