package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

        private static WebDriver driver;

        public static WebDriver getDriver(String browser) {
            return driver == null ? createDriver(browser) : driver;
        }

        private static WebDriver createDriver(String browser) {
            switch (browser) {
                case "Firefox": {
                    driver = new FirefoxLatestDriver().createInstance();
                }
                break;
                case "Chrome": {
                    driver = new ChromeLatestDriver().createInstance();
                }
                break;
            }
            return driver;
        }

        private static class FirefoxLatestDriver implements IDriver {
            public WebDriver createInstance() {
                System.setProperty("webdriver.gecko.driver", ".\\src\\main\\resources\\geckodriver.exe");
                return  new FirefoxDriver();
            }
        }

        private static class ChromeLatestDriver implements IDriver {
            public WebDriver createInstance() {
                System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
                return new ChromeDriver();
            }
        }
    }

