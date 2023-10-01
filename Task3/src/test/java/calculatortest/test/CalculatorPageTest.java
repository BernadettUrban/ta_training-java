package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.googlepricecalculatorpages.CalculatorPage;
import calculatortest.googlepricecalculatorpages.Estimate;
import calculatortest.googlepricecalculatorpages.StartPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CalculatorPageTest {
    protected WebDriver driver;
    private CalculatorPage calculatorPage;
    StartPage startPage;
    Estimate estimate;

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
    public void estimatedCostIsCalculatedCorrectly(){
       estimate =  calculatorPage.getIframe()
                .addSpecifications();

       String actual = estimate.totalEstimatedCost();
       String expected = "Total Estimated Cost: USD 1,081.20 per 1 month";

        assertThat(actual, equalTo(expected));
    }
}
