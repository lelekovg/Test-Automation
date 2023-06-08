import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.By.xpath;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.*;
import io.github.bonigarcia.wdm.WebDriverManager;



public class WebDriverHW2Test {
    WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void profileSetUp() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        WebDriverManager.chromedriver().setup();

    }

    @BeforeMethod(alwaysRun = true)
    public void testsSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://avic.ua/");
    }

    @Test(priority = 1)
    public void checkingSearchResults() {
        waitForElementLocateBy(driver, By.id("input_search")).sendKeys("iPhone 13");
        List<WebElement> searchBtn = waitForBatLocatedBy(driver, "//button [contains(@class,'button-reset search-btn')]");
        searchBtn.get(0).click();
        List<WebElement> searchResults = waitForBatLocatedBy(driver, "//div [contains(@class,'prod-cart__prise-new')]");
        assertTrue(searchResults.size() > 0, "Search results are empty!");
    }

    @Test(priority = 2)
    public void checkingFilterResults() {
        driver.findElement(xpath("//input[@id='input_search']")).sendKeys("apple watch");
        driver.findElement(xpath("//button[@class='button-reset search-btn']")).click();
        driver.findElement(By.xpath("//label[@for='fltr-category-662']")).click();
        List<WebElement> elementList = driver.findElements(xpath("//div[@class='prod-cart__descr']"));
        for (WebElement webElement : elementList) {
            assertTrue(webElement.getText().contains("Навушники"));
        }
    }

    @Test(priority = 3)
    public void checkOfCountOfProductsOnThePage() {
        driver.get("https://avic.ua/apple-store");
        waitForElementLocateBy(driver, xpath("//div[(@class='brand-box__title')]//a[(@href= 'https://avic.ua/ua/macbook')]")).click();
        List<WebElement> elementsList = driver.findElements(xpath("//div[contains(@class,'prod-cart')]//a[contains(@class, 'prod-cart__buy')]"));
        int actualElementsSize = elementsList.size();
        assertEquals(actualElementsSize, 12);
    }

    @Test(priority = 4)
    public void checkThanYouCanBuyProduct() {
        driver.get("https://avic.ua/ua/apple-watch-umnie-chasi");
        waitForElementLocateBy(driver, xpath("//div[contains(@class,'prod-cart')]//a[contains(@class,'prod-cart__buy')]")).click();
        assertNotNull(driver.findElement(xpath("//div[(@class='ttl' and text()='Кошик')]")));
    }

    @Test(priority = 5)
    public void checkThanTheProductIsInCart() {
        driver.get("https://avic.ua/ua/apple-watch-umnie-chasi");
        String CountOfProduct;
        waitForElementLocateBy(driver, By.xpath("//div[contains(@class,'prod-cart')]//a[contains(@class, 'prod-cart__buy')][1]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("js_cart")));
        waitForElementLocateBy(driver, By.xpath("//*[@id='js_cart']/div[2]/div[2]/a[1]")).click();
        CountOfProduct = waitForElementLocateBy(driver, By.xpath("//div[contains(@class,'header-bottom__cart')]//div[contains(@class,'js_cart_count')]")).getText();
        assertEquals(CountOfProduct, "1");
    }

    @Test(priority = 6)
    public void checkCorrectSorting() {
        driver.get("https://avic.ua/ua/search-results/available--on_categories--987?query=canon");
        waitForElementLocateBy(driver, xpath("  (//span[contains(@id, 'select2-sort')])[2]")).click();
        waitForElementLocateBy(driver, xpath("//li[contains(@id , 'select2-sort') and contains(@id , 'priceasc')]")).click();
        String nextPrice = waitForElementLocateBy(driver, xpath("(//div[@class='prod-cart__prise-new'])[2]")).getText();
        String minPrice = waitForElementLocateBy(driver, xpath("(//div[@class='prod-cart__prise-new'])[1]")).getText();
        int min = Integer.parseInt(minPrice.replaceAll("[^0-9]", ""));
        int next = Integer.parseInt(nextPrice.replaceAll("[^0-9]", ""));
        assertTrue(min <= next);

    }

    private static List<WebElement> waitForBatLocatedBy(WebDriver driver, String xpathExpression) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath(xpathExpression)));
    }

    private static WebElement waitForElementLocateBy(WebDriver driver, By xpath) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}
