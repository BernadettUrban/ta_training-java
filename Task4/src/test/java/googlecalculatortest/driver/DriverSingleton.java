package googlecalculatortest.driver;

import googlecalculatortest.service.PropertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSingleton {

    public static final String BROWSER = "browser";
    private static WebDriver driver;

    public DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            switch (PropertyFileReader.getEnvironment(BROWSER)) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;


            }
            driver.manage().window().maximize();
        }
        return driver;
    }


    public static void closeDriver() {
        driver.manage().deleteAllCookies();

        driver.quit();
        driver = null;
    }
}
