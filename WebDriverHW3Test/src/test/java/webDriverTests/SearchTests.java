package webDriverTests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SearchTests extends BaseTest {

    private final String SEARCH_KEYWORD = "canon";
    Integer minPrice, nextPrice;


    @Test(priority = 1)
    public void checkThatUrlContainsSearchWord() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        String EXPECTED_SEARCH_QUERY = "query=canon";
        assertTrue(getDriver().getCurrentUrl().contains(EXPECTED_SEARCH_QUERY));

    }

    @Test(priority = 2)
    public void checkElementsAmountOnSearchPage() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        assertEquals(getSearchPage().getSearchResultsCount(), 12);
    }

    @Test(priority = 3)
    public void checkSortedProduct() {
        getHomePage().searchByKeyword(SEARCH_KEYWORD);
        getSearchPage().clickCheckBoxPhoto();
        getSearchPage().clickCheckBoxAvailable();
        getSearchPage().clickToSortedButton();
        getSearchPage().clickToSortedASC();
        minPrice = Integer.parseInt(getSearchPage().getMinPriceCanonPhoto().replaceAll("[^0-9]", ""));
        nextPrice = Integer.parseInt(getSearchPage().getNextPriceCanonPhoto().replaceAll("[^0-9]", ""));
        assertTrue(minPrice <= nextPrice);

    }
}
