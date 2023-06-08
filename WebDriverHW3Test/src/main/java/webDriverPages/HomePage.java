package webDriverPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'open-cat')]//span[@class='sidebar-item']")
    private WebElement catalogButton;
    @FindBy(xpath = "//div[(@class='menu-lvl first-level')]//a[contains(@href, 'apple-store')]")
    private WebElement appleStore;

    @FindBy(xpath = "//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'js_cart_count')]")
    private WebElement countOfProductInCart;

    @FindBy(xpath = "//input[@id='input_search']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@class='button-reset search-btn']")
    private WebElement searchButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickAppleStoreButton() {
        appleStore.click();
    }

    public void clickCatalogButton() {
        catalogButton.click();
    }

    public String getTextFromCartsCount() {
        return countOfProductInCart.getText();
    }


    public void searchByKeyword(final String keyword) {
        searchInput.sendKeys(keyword);
        searchButton.click();
    }

}