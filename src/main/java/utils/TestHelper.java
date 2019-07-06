package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestHelper {
    public static final String CSS = "CSS";
    public static final String XPATH = "XPath";

    public static int convertStringToInt(String price) {
        String[] list = price.split(" ");
        return list.length == 2 ? Integer.parseInt(list[0]) : Integer.parseInt(list[0] + list[1]);
    }

    public static void waitForPageToLoad(WebDriver driver, String locator, String type) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        switch (type) {
            case CSS: {
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(locator))));
                break;
            }
            case XPATH: {
                wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator))));
                break;
            }
        }
    }

    public static void waitForJavaScriptComplete(WebDriver driver) {
        new WebDriverWait(driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }
    public static void waitForAjaxComplete(WebDriver driver) {
        new WebDriverWait(driver, 20).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return jQuery.active").toString().equals("0"));
    }

}
