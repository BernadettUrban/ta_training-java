package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.googlepricecalculatorpages.CalculatorPage;
import calculatortest.googlepricecalculatorpages.StartPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalculatorPageTest {
    protected WebDriver driver;
    private CalculatorPage calculatorPage;
    StartPage startPage;

    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        calculatorPage = new CalculatorPage(driver);
        calculatorPage.openPage();
        calculatorPage.clickOkButton();

    }

    // @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
    @Test
    public void something(){
        calculatorPage.getIframe()
                .addSpecifications();
    }
}
