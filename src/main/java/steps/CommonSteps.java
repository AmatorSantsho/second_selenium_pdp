package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import page.CarPage;
import page.HomePage;

public class CommonSteps {
    private WebDriver driver;
    private HomePage homePage;
    private CarPage carPage;

    public CommonSteps(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage openHomePage() {
        homePage = new HomePage(driver);
        return homePage;
    }

    public String getColorFromMenuHeader(HomePage homePage)
    {
        return homePage.getHeaderColor();
    }
    public String getMenuHeader(){
        return homePage.getHeaderMenuContent();
    }

    public boolean isPriceOfFirstCarInsideRange(String startPrice, String endPrice ){
       return carPage.isPriceInsideRange(startPrice, endPrice);
    }

    public CarPage selectCar(String carBrand) {
        homePage.navigateToCarMenu();
        homePage.clickOnCar(carBrand);
        carPage = new CarPage(driver);
        return carPage;
    }

    public CarPage filterByPrice(String startPrice, String endPrice){
        carPage.setStartPrice(startPrice);
        carPage.setEndPrice(endPrice);
        return new CarPage(driver);
    }
}
