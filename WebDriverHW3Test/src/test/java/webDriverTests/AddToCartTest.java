package webDriverTests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class AddToCartTest extends BaseTest {

    @Test
    void checkThanTheProductIsInCart() {
        getHomePage().clickCatalogButton();
        getHomePage().clickAppleStoreButton();
        getAppleStorePage().clickAppleWatchButton();
        getAppleWatchPage().waitForPageLoadComplete(30);
        getAppleWatchPage().clickBuyFirstWatchButton();
        getAppleWatchPage().waitVisibilityOfElement(30, getAppleWatchPage().getAddToCartPopup());
        getAppleWatchPage().clickContinueShoppingButton();
        String EXPECTED_COUNT_OF_PRODUCT_IN_CART = "1";
        assertEquals(getHomePage().getTextFromCartsCount(), EXPECTED_COUNT_OF_PRODUCT_IN_CART);

    }
}