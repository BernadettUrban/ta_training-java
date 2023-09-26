package packageName.driver;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class DriverSingleton {
    private static WebDriver driver;


    public DriverSingleton(){}

    public static WebDriver getDriver(){
        if (null == driver){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            }
            driver.manage().window().maximize();

       return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
