package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utils.TestHelper;

public class CarPage {
    private WebDriver driver;
    private static String MIN_PRICE = "//select[@name='min-price']";
    private static String MAX_PRICE = "//select[@name='max-price']";
    private static String SEARCH_RESULT_PRICE = "//*[starts-with(@id,'car_')]/td[3]/div/p[2]";
    private static String SPINNER = ".b-update-btn-1.progress";

    public CarPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setStartPrice(String startPrice) {
        Select prices = new Select(driver.findElement(By.xpath(MIN_PRICE)));
        prices.selectByVisibleText(startPrice);
        TestHelper.waitForPageToLoad(driver, SPINNER, TestHelper.CSS);
        TestHelper.waitForJavaScriptComplete(driver);
        TestHelper.waitForAjaxComplete(driver);

    }

    public void setEndPrice(String endPrice) {
        Select prices = new Select(driver.findElement(By.xpath(MAX_PRICE)));
        prices.selectByVisibleText(endPrice);
        TestHelper.waitForPageToLoad(driver, SPINNER, TestHelper.CSS);
        TestHelper.waitForJavaScriptComplete(driver);
        TestHelper.waitForAjaxComplete(driver);
    }

    public boolean isPriceInsideRange(String startPrice, String endPrice) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        TestHelper.waitForPageToLoad(driver, SEARCH_RESULT_PRICE, TestHelper.XPATH);
        String price = driver
                .findElement(By.xpath(SEARCH_RESULT_PRICE))
                .getText();
        String[] all = price.split("\n");

        int currentPrice = TestHelper.convertStringToInt(all[0]);
        System.out.println(currentPrice);
        int start = TestHelper.convertStringToInt(startPrice);
        System.out.println(start);
        int end = TestHelper.convertStringToInt(endPrice);
        System.out.println(end);
        return currentPrice >= start && currentPrice <= end;
    }

}
