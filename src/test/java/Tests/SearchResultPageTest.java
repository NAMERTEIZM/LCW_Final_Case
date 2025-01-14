package Tests;

import Pages.SearchResultPage;
import org.junit.jupiter.api.Test;

public class SearchResultPageTest extends BaseTest{

    final String loginURL ="https://www.lcw.com/giris";

    @Test
    public void SearchCategory()
    {
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.HoverAndClickForSearch();
    }









}
