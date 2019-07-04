package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestHelper;

public class CarPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static int TIMEOUT = 10;
    private static String MIN_PRICE = "//select[@name='min-price']";
    private static String MAX_PRICE = "//select[@name='max-price']";
    private static String SEARCH_RESULT_PRICE = "//*[starts-with(@id,'car_')]/td[3]/div/p[2]";

    public CarPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT);
    }

    public CarPage setStartPrice(String startPrice) {
        Select prices = new Select(driver.findElement(By.xpath(MIN_PRICE)));
        prices.selectByVisibleText(startPrice);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(SEARCH_RESULT_PRICE))));

        return this;
    }

    public CarPage setEndPrice(String endPrice) {
        Select prices = new Select(driver.findElement(By.xpath(MAX_PRICE)));
        prices.selectByVisibleText(endPrice);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(SEARCH_RESULT_PRICE))));
        return this;
    }

    public boolean isPriceInsideRange(String startPrice, String endPrice) {
        String price = driver
                .findElement(By.xpath(SEARCH_RESULT_PRICE))
                .getText();
        String[] all = price.split("\n");

        int p = TestHelper.convertStringToInt(all[0]);
        int startP = TestHelper.convertStringToInt(startPrice);
        int endP = TestHelper.convertStringToInt(endPrice);
        if (p >= startP && p <= endP)
            return true;
        return true;
    }
}
