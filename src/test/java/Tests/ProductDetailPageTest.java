package Tests;

import Pages.ProductDetailPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Epic("Product Operations")
@Feature("Product Detail Functionality")
public class ProductDetailPageTest extends BaseTest {

    final String URL = "https://www.lcw.com/kapusonlu-kiz-cocuk-mont-bej-o-4411831";

    @Test
    @Story("Select Size and Add to Cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test to verify size selection and adding a product to the cart works correctly.")
    @Step("Performing test: Select size, add to cart, and verify details.")
    public void testSelectSizeAndAddToCart() {
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        driver.navigate().to(URL);
        Allure.step("Navigated to product detail page: " + URL);

        // Select available size and add to cart
        productDetailPage.selectAvailableSizeAndAddToCart();
        Allure.step("Selected available size and added product to cart.");

        // Assertions for price, color, and size
        try {
            Assertions.assertEquals(
                    productDetailPage.expectedPriceText(),
                    productDetailPage.priceText(),
                    "Price mismatch"
            );
            Allure.step("Price validation successful: " + productDetailPage.priceText());

            Assertions.assertEquals(
                    "Koyu Bej",
                    productDetailPage.colourText(),
                    "Color mismatch"
            );
            Allure.step("Color validation successful: " + productDetailPage.colourText());

            Assertions.assertEquals(
                    "5-6 Yaş",
                    productDetailPage.sizeText(),
                    "Size mismatch"
            );
            Allure.step("Size validation successful: " + productDetailPage.sizeText());

        } catch (AssertionError e) {
            Allure.addAttachment("Assertion Error", e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw exception to fail the test
        }

        // Additional actions
        productDetailPage.increaseAndDecreaseQuantity();
        Allure.step("Verified increase and decrease quantity functionality.");

        productDetailPage.addToFavoritesAndVerify();
        Allure.step("Added product to favorites and verified.");
    }
}
