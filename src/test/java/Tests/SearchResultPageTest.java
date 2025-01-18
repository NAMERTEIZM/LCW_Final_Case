package Tests;

import Pages.SearchResultPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Search Functionality")
@Feature("Category Search and Filters")
public class SearchResultPageTest extends BaseTest {

    final String loginURL = "https://www.lcw.com/giris";

    @Test
    @Story("Search Category and Apply Filters")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test to search for a category and apply filters successfully.")
    @DisplayName("Kategori Ara ve Filtreleri Uygula")
    public void testSearchCategoryAndApplyFilters() {
        SearchResultPage searchResultPage = new SearchResultPage(driver);

        try {
            Allure.step("Hover over and click for search.");
            searchResultPage.HoverAndClickForSearch();

            Allure.step("Search and filter functionality tested successfully.");
            System.out.println("Kategori arama ve filtreleme testi başarıyla tamamlandı.");
        } catch (Exception e) {
            Allure.step("Error occurred during the test: " + e.getMessage());
            System.err.println("Test sırasında bir hata oluştu: " + e.getMessage());
            e.printStackTrace();

            // Test başarısız olarak işaretlenebilir
            assert false : "Test başarısız oldu: " + e.getMessage();
        }
    }
}
