package webDriverPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.By.id;

public class AppleWatchPage extends BasePage {

    private static final String ADD_TO_CART_POPUP = "js_cart";
    @FindBy(xpath = "//*[@id='js_cart']/div[2]/div[2]/a[1]")
    private WebElement continueShoppingButton;

    @FindBy(xpath = "//div[contains(@class,'prod-cart')]//a[contains(@class,'prod-cart__buy')][1]")
    private WebElement firstFieldWatch;

    public AppleWatchPage(WebDriver driver) {
        super(driver);
    }

    public void clickBuyFirstWatchButton() {
        firstFieldWatch.click();
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public By getAddToCartPopup() {
        return id(ADD_TO_CART_POPUP);
    }


}
