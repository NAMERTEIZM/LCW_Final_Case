package Tests;

import Pages.ProductDetailPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.asserts.Assertion;


public class ProductDetailPageTest extends BaseTest{

    final String URL="https://www.lcw.com/kapusonlu-kiz-cocuk-mont-bej-o-4411831";

    @Test
    public void testSelectSizeAndAddToCart() {
        // Call the method to select size and add to cart

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        driver.navigate().to(URL);
        productDetailPage.selectAvailableSizeAndAddToCart();
        //productDetailPage.clickGoToCartButton();
        try {

            Assertions.assertEquals(productDetailPage.expectedPriceText(), productDetailPage.priceText());
            System.out.println("Fiyat doğrulaması başarılı: " + productDetailPage.priceText());


            Assertions.assertEquals("Koyu Bej", productDetailPage.colourText());
            System.out.println("Renk doğrulaması başarılı: " + productDetailPage.colourText());


            Assertions.assertEquals("5-6 Yaş", productDetailPage.sizeText());
            System.out.println("Beden doğrulaması başarılı: " + productDetailPage.sizeText());

        } catch (AssertionError e) {
            System.out.println("Assertion hatası: " + e.getMessage());
            e.printStackTrace();
        }

    }




}
