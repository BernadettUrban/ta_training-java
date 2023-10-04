package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.emailpages.EmailGeneratorPage;
import calculatortest.googlepricecalculatorpages.CalculatorPage;
import calculatortest.googlepricecalculatorpages.Estimate;
import calculatortest.googlepricecalculatorpages.StartPage;
import calculatortest.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CalculatorPageTest {
    protected WebDriver driver;
    private CalculatorPage calculatorPage;
    Estimate estimate;
    EmailGeneratorPage emailGeneratorPage;
    StringUtils stringUtils = new StringUtils();

    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        calculatorPage = new CalculatorPage(driver);
        calculatorPage.openPage();
        calculatorPage.clickOkButton();
        estimate =  calculatorPage.getIframe()
                .addSpecifications(stringUtils.NUMBER_OF_INSTANCES);

    }

    // @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
    @Test
    public void estimatedCostIsCalculatedCorrectly(){
       String actual = estimate.totalEstimatedCost();
       String expected = "Total Estimated Cost: USD 1,081.20 per 1 month";
      //  assertThat(actual, equalTo(expected));
    }


    @Test
    public void insertEmailIntoForm() throws IOException, UnsupportedFlavorException {

        emailGeneratorPage = new EmailGeneratorPage(driver);
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();


        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(stringUtils.BASE_URL_FOR_EMAIL_GENERATOR);

        emailGeneratorPage.clickAgreeButton();
        emailGeneratorPage.copyEmailToClipBoard();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String email = (String) clipboard.getData(DataFlavor.stringFlavor);

        driver.switchTo().window(originalWindow);
        calculatorPage.getIframe();
        estimate.sendEstimateInEmail(email);

        //boolean emailIsEmpty = false;
       // assertThat(Boolean.valueOf(email.isEmpty()), equalTo(emailIsEmpty));


    }
}
