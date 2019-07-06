package test;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.HomePage;
import steps.CommonSteps;

public class TestCarSearch {
    private WebDriver driver;
    private CommonSteps commonSteps;
    private static String EXPECTED_COLOR_HEX = "#ffdd22";
    private static String EXPECTED_MENU_CONTENT = "Каталог Новости Автобарахолка Дома и квартиры Услуги Барахолка Форум";
    private String CAR;
    private String START_PRICE;
    private String END_PRICE;

    @BeforeSuite
    @Parameters({"browser", "car", "startPrice", "endPrice"})
    public void setUp(String browser, String car, String startPrice, String endPrice) {
        driver = DriverFactory.getDriver(browser);
        commonSteps = new CommonSteps(driver);
        CAR = car;
        START_PRICE = startPrice;
        END_PRICE = endPrice;
    }

    @AfterSuite
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void testContentOnHomePage() {
        HomePage homePage = commonSteps.openHomePage();
        String color = commonSteps.getColorFromMenuHeader(homePage);
        Assert.assertEquals(EXPECTED_COLOR_HEX, color);
        String menuContent = commonSteps.getMenuHeader();
        Assert.assertEquals(EXPECTED_MENU_CONTENT, menuContent);
    }

    @Test
    public void testCarSearchByPrice() {
        commonSteps.openHomePage();
        commonSteps.selectCar(CAR);
        commonSteps.filterByPrice(START_PRICE, END_PRICE);
        Assert.assertTrue(commonSteps.isPriceOfFirstCarInsideRange(START_PRICE, END_PRICE));
    }
}
