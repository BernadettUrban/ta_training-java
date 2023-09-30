package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.pages.StartPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StartPageTest {
    protected WebDriver driver;
    private StartPage startPage;

    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        startPage = new StartPage(driver);
        startPage.openPage();

    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void search(){
        startPage.performSearch();
    }

}
