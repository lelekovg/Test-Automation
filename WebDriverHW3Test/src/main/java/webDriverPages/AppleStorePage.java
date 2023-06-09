package webDriverPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppleStorePage extends BasePage {

    @FindBy(xpath = "//div[(@class='brand-box__title')]//a[(@href= 'https://avic.ua/ua/apple-watch-umnie-chasi')]")
    private WebElement appleWatchButton;

    public AppleStorePage(WebDriver driver) {
        super(driver);
    }

    public void clickAppleWatchButton() {
        appleWatchButton.click();
    }

}