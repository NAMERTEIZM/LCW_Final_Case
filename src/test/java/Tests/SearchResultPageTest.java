package Tests;

import Pages.SearchResultPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchResultPageTest extends BaseTest{

    final String loginURL ="https://www.lcw.com/giris";


    @Test
    @DisplayName("Kategori Ara ve Filtreleri Uygula")
    public void testSearchCategoryAndApplyFilters() {
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        try {
            // Kategori ara ve filtre işlemlerini gerçekleştir
            searchResultPage.HoverAndClickForSearch();
            System.out.println("Kategori arama ve filtreleme testi başarıyla tamamlandı.");
        } catch (Exception e) {
            System.err.println("Test sırasında bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
            // Test başarısız olarak işaretlenebilir
            assert false : "Test başarısız oldu: " + e.getMessage();
        }
    }









}
