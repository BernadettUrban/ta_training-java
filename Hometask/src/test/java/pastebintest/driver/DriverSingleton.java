package pastebintest.driver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverSingleton {
    private static WebDriver driver;


    public DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();

        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
