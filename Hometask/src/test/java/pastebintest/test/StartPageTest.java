package pastebintest.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pastebintest.driver.DriverSingleton;
import pastebintest.pages.StartPage;


public class StartPageTest {

    public static final String CODE = "Hello from WebDriver";
    protected WebDriver driver;

    @BeforeMethod()
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void userCanWriteCode() {
        new StartPage(driver)
                .openPage()
                .clickAgreeButton()
                .writeInInputField();

    }

    @Test
    public void userCanSelectExpiry() {
        new StartPage(driver)
                .openPage()
                .clickAgreeButton()
                .selectExpiry();

    }

    @Test
    public void userCanGiveNameToPasteBin() {
        new StartPage(driver)
                .openPage()
                .clickAgreeButton()
                .enterPasteName();
    }
}
