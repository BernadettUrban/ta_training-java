package calculatortest.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSingleton {

    private static WebDriver driver;

    public DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();

            options.addArguments("start-maximized");

            options.addArguments("disable-infobars");

            options.addArguments("--disable-extensions");

            driver = new ChromeDriver(options);

        }
        driver.manage().window().maximize();

        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
