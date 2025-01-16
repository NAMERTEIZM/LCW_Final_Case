package Tests;

import Pages.ProductDetailPage;
import org.junit.jupiter.api.Test;


public class ProductDetailPageTest extends BaseTest{

    final String URL="https://www.lcw.com/kapusonlu-kiz-cocuk-mont-bej-o-4411831";

    @Test
    public void testSelectSizeAndAddToCart() {
        // Call the method to select size and add to cart

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        driver.navigate().to(URL);
        productDetailPage.selectAvailableSizeAndAddToCart();
        //productDetailPage.clickGoToCartButton();
    }




}
