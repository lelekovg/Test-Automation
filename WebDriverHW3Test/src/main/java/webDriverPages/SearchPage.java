package webDriverPages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.JavascriptExecutor;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//div[@class='prod-cart__descr']")
    private List<WebElement> searchQuery;

    @FindBy(xpath = "(//span[contains(@id, 'select2-sort')])[2]")
    private WebElement sortedButton;

    @FindBy(xpath = "//li[contains(@id , 'select2-sort') and contains(@id , 'priceasc')]")
    private WebElement sortedASC;

    @FindBy(xpath = "//ul[@class='select2-results__options']/li[2]")
    private List<WebElement> sortedASCList;

    @FindBy(xpath = "//div/input[@type='checkbox' and @value='987']")
    private WebElement checkBoxPhoto;

    @FindBy(xpath = "//input[@type='checkbox' and @name='available']")
    private WebElement checkBoxAvailable;


    @FindBy(xpath = "(//div[@class='prod-cart__prise-new'])[1]")
    private WebElement minPrice;

    @FindBy(xpath = "(//div[@class='prod-cart__prise-new'])[2]")
    private WebElement nextPrice;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getSearchResultList() {
        return searchQuery;
    }

    public int getSearchResultsCount() {
        return getSearchResultList().size();
    }

    public String getMinPriceCanonPhoto() {
        return minPrice.getText();
    }

    public String getNextPriceCanonPhoto() {
        return nextPrice.getText();
    }


    public void clickCheckBoxPhoto() {
        WebElement element = checkBoxPhoto;
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void clickCheckBoxAvailable() {
        WebElement element = checkBoxAvailable;
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


    public void clickToSortedASC() {
        sortedASC.click();
    }

    public void clickToSortedButton() {
        int i = sortedASCList.size();
        while (i != 0) {
            sortedButton.click();
            i = sortedASCList.size();
        }

        sortedButton.click();

    }

}
