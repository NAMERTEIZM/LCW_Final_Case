package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductDetailPage extends BasePage {

    // Locators
    private By availableSizes = By.xpath("//button[not(contains(@class, 'option-size-box__out-of-stock')) and contains(@class, 'option-size-box')]");
    private By addToCartButton = By.className("add-to-card"); // Replace with the actual 'Add to Cart' button ID
    private By goToCartButton = By.xpath("//button[contains(text(), 'Sepete Git')]");
    private By myCart = By.xpath("//*[@id=\"header__container\"]/header/div[1]/div[3]/div/div[2]/a/span");

    // After Cart
    private By size = By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[2]/span[1]/strong");
    private By colour = By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/div[2]/span[2]/strong");
    private By price = By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/span");
    private By expectedPrice =By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[3]/div[2]/div[3]/div/span[2]");
    private By plusQuantity = By.id("Cart_AddQuantity_1766500558");
    private By minusQuantity = By.id("Cart_RemoveQuantity_1766500558");
    private By myFavs = By.xpath("//*[@id=\"header__container\"]/header/div[1]/div[3]/div/div[1]/a/span");



    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public String sizeText()
    {
        WebElement sizeElement = driver.findElement(size);
        return sizeElement.getText();

    }
    public String colourText()
    {
        WebElement colourElement = driver.findElement(colour);
        return colourElement.getText();

    }
    public String priceText()
    {
        WebElement priceElement = driver.findElement(price);
        return priceElement.getText();

    }
    public String expectedPriceText()
    {
        WebElement expectedPriceElement = driver.findElement(expectedPrice);
        return expectedPriceElement.getText();

    }

    public void selectAvailableSizeAndAddToCart() {
        try {
            // Find all available sizes
            List<WebElement> sizes = driver.findElements(availableSizes);
            if (!sizes.isEmpty()) {
                // Select the first available size
                sizes.get(0).click();
                System.out.println("First available size selected.");

                // Wait for a moment to see the selection
                Thread.sleep(3000);

                // Click the 'Add to Cart' button
                try {
                    driver.findElement(addToCartButton).click();
                    System.out.println("Product added to the cart successfully.");
                } catch (Exception e) {
                    System.out.println("Failed to add the product to the cart. Error: " + e.getMessage());
                }

            } else {
                System.out.println("No sizes available in stock.");
            }

            // Wait for a moment to observe what happened
            Thread.sleep(5000);

            // And go to Cart
            WebElement mycart = driver.findElement(myCart);
            mycart.click();
            Thread.sleep(5000);


        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
/*
    public void clickGoToCartButton() {
        try {

            // Wait for the pop-up to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
           /* WebElement cartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartButton));

            // Click the "Sepete Git" button
            cartButton.click();
            System.out.println("Clicked on 'Sepete Git' button.");

        } catch (Exception e) {
            System.out.println("Failed to click on 'Sepete Git' button. Error: " + e.getMessage());
        }
    }
*/






}
