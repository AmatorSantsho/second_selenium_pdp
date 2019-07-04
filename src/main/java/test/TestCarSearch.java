package test;

import driver.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.CarPage;
import page.HomePage;
import steps.CommonSteps;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class TestCarSearch {
    private WebDriver driver;
    private CommonSteps commonSteps;
    private static String EXPEXTED_COLOR_HEX = "#ffdd22";
    private static String EXPECTED_MENU_CONTENT = "Каталог Новости Автобарахолка Дома и квартиры Услуги Барахолка Форум";
    private static String CAR_CITROEN = "Citroen";
    private static String START_PRICE = "6000 $";
    private static String END_PRICE = "8000 $";

    @BeforeSuite
    @Parameters("browser")
    public void setUp(String browser) {
        driver = DriverFactory.getDriver(browser);
        commonSteps = new CommonSteps(driver);
    }

    @AfterSuite
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void testContentOnHomePage() {
        HomePage homePage = commonSteps.openHomePage();
        String color = commonSteps.getColorFromMenuHeader(homePage);
        Assert.assertEquals(EXPEXTED_COLOR_HEX, color);
        String menuContent = commonSteps.getMenuHeader();
        Assert.assertEquals(EXPECTED_MENU_CONTENT, menuContent);
    }

    @Test
    public void testCarSearchByPrice() {
        commonSteps.openHomePage();
        commonSteps.selectCar(CAR_CITROEN);
        commonSteps.filterByPrice(START_PRICE, END_PRICE);
        Assert.assertTrue(commonSteps.isPriceOfFirstCarInsideRange(START_PRICE, END_PRICE));
    }


}
