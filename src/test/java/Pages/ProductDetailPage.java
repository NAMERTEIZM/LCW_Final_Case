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
    private By plusQuantity = By.cssSelector("a.oq-up.plus");
    private By minusQuantity = By.cssSelector("a.oq-down.minus");
    private By myFavs = By.xpath("//*[@id=\"header__container\"]/header/div[1]/div[3]/div/div[1]/a/span");
    private By addFavs = By.xpath("//i[contains(@class, 'fa') and contains(@class, 'fa-heart')]\n");


    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    public WebElement addFavsElement()
    {

        return driver.findElement(addFavs);

    }

    public WebElement myFavsElement()
    {

        return driver.findElement(myFavs);

    }


    public WebElement minusQuantityElement()
    {

        return driver.findElement(minusQuantity);

    }

    public WebElement plusQuantityElement()
    {

        return driver.findElement(plusQuantity);

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

    // Method 1: Increase and Decrease Quantity
    public void increaseAndDecreaseQuantity() {
        try {
            // Click on the increase quantity button
            plusQuantityElement().click();
            System.out.println("Quantity increased successfully.");
            Thread.sleep(5000); // Wait for 5 seconds to observe the change

            // Click on the decrease quantity button
            minusQuantityElement().click();
            System.out.println("Quantity decreased successfully.");
            Thread.sleep(5000); // Wait for 5 seconds to observe the change

        } catch (Exception e) {
            System.out.println("Error during quantity adjustment: " + e.getMessage());
        }
    }

    // Method 2: Add to Favorites and Verify
    public void addToFavoritesAndVerify() {
        try {
            // Click on the 'Add to Favorites' button
            addFavsElement().click();
            System.out.println("Product added to favorites.");
            Thread.sleep(5000); // Wait for 5 seconds to ensure the product is added

            // Click on the 'My Favorites' button to navigate to the favorites page
            myFavsElement().click();
            System.out.println("Navigated to the favorites page.");
            Thread.sleep(10000); // Wait for 10 seconds to observe the favorites page

        } catch (Exception e) {
            System.out.println("Error while adding to favorites or navigating to favorites page: " + e.getMessage());
        }
    }




}
