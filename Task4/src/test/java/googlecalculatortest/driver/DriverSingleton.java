package googlecalculatortest.driver;

import googlecalculatortest.service.PropertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSingleton {

    private static WebDriver driver;
    public static final String BROWSER = "browser";

    public DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver){
            switch (PropertyFileReader.getEnvironment(BROWSER)){
                case "edge": {
                    WebDriverManager.edgedriver();
                    driver = new EdgeDriver();
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
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
