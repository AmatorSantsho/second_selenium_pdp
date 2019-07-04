package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage {
    private static String URL = "https://www.onliner.by/";
    private static String HEADER_MENU = "//*[@id='container']/div/div/header/div[2]/div/nav/ul[1]";
    private static String HEADER_MENU_ITEM = HEADER_MENU + "/li";
    private static String MENU_ITEM_AUTO_BARAHOLKA = HEADER_MENU + "/li[3]";
    private static String DROPDOWN_MENU_OF_AUTO_BARAHOLKA = MENU_ITEM_AUTO_BARAHOLKA + "/div/div/div/div/div[1]/div[1]/a";
    private WebDriver driver;
    private WebDriverWait wait;
    private static int TIMEOUT = 10;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT);
        driver.get(URL);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(HEADER_MENU))));
    }

    public String getHeaderMenuContent() {
        List<WebElement> menuItems = driver.findElements(By.xpath(HEADER_MENU_ITEM));
        java.util.Iterator<WebElement> iterator = menuItems.iterator();
        StringBuilder menuContent = new StringBuilder();

        while (iterator.hasNext()) {
            WebElement item = iterator.next();
            String s = item.findElement(By.xpath(".//a")).getText();
            menuContent.append(s);
            menuContent.append(" ");
        }
        String result = menuContent.toString();
        return result.trim();
    }

    public String getHeaderColor() {
        String c = driver.findElement(By.xpath(HEADER_MENU))
                .getCssValue("background-color");
        Color color = Color.fromString(c);
        return color.asHex();
    }

    public void navigateToCarMenu() {
        Actions action = new Actions(driver);
        WebElement elem = driver.findElement(By.xpath(MENU_ITEM_AUTO_BARAHOLKA));
        action.moveToElement(elem);
        action.perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(DROPDOWN_MENU_OF_AUTO_BARAHOLKA))));
    }

    public void clickOnCar(String car) {
        WebElement cars = driver.findElement(By.xpath("//*[@class='b-main-navigation__dropdown-advert-sign' and contains(text(),'" + car + "')] "));
        cars.click();


    }
}
