package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.emailpages.EmailGeneratorPage;
import calculatortest.googlepricecalculatorpages.CalculatorPage;
import calculatortest.googlepricecalculatorpages.Estimate;
import calculatortest.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

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
        estimate =  calculatorPage.switchToMyFrame()
                .addSpecifications(stringUtils.NUMBER_OF_INSTANCES);

    }

    // @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
    @Test(priority=1)
    public void estimatedCostIsCalculatedCorrectly(){
       String actual = estimate.totalEstimatedCost();
       String expected = "Total Estimated Cost: USD 1,081.20 per 1 month";
        assertThat(actual, equalTo(expected));
    }
    @Test(priority=2)
    public void monthlyCostIsCorrect(){
        String actualString = estimate.getMonthlyEstimate();
        boolean actual = estimate.totalEstimatedCost().contains(actualString);
        System.out.println(actualString);
        System.out.println(estimate.totalEstimatedCost());
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }
    @Test(priority=3)
    public void regionIsCorrectInEstimate(){
        String actualString = estimate.getRegion();
        boolean actual = actualString .contains(stringUtils.REGION);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }
    @Test(priority=4)
    public void numberOfEnginesIsCorrect(){
        String actualString = estimate.getNumberOfEngines();
        boolean actual = actualString .contains(stringUtils.NUMBER_OF_INSTANCES);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }
    @Test(priority=5)
    public void commitmentTermIsCorrect(){
        String actualString = estimate.getCommitmentTerm();
        boolean actual = actualString .contains(stringUtils.COMMITMENT_TERM);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }



    @Test(priority=6)
    public void getProvisioningModel(){
        String actualString = estimate.getProvisioningModel();
        boolean actual = actualString .contains(stringUtils.PROVISIONING_MODEL);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }


    @Test(priority=7)


    public void instanceTypeIsCorrect(){
        String actualString = estimate.getInstanceType();
        boolean actual = actualString .contains(stringUtils.INSTANCE_TYPE);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }
    @Test(priority=8)
    public void operatingSystemIsCorrect(){
        String actualString = estimate.getOperatingSystem();
        boolean actual = actualString .contains(stringUtils.OPERATING_SYSTEM);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }
    @Test(priority=9)
    public void localSSDIsCorrect(){
        String actualString = estimate.getLocalSSD();
        boolean actual = actualString .contains(stringUtils.LOCAL_SSD);
        assertThat(Boolean.valueOf(actual), equalTo(true));
    }



  //  @Test(priority=10)
    public void insertEmailIntoForm() throws IOException, UnsupportedFlavorException {

        emailGeneratorPage = new EmailGeneratorPage(driver);
        String originalWindow = driver.getWindowHandle();

        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(stringUtils.BASE_URL_FOR_EMAIL_GENERATOR);

        emailGeneratorPage.clickAgreeButton();
        emailGeneratorPage.copyEmailToClipBoard();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String email = (String) clipboard.getData(DataFlavor.stringFlavor);

        driver.switchTo().window(originalWindow);
        calculatorPage.switchToMyFrame();


        //boolean emailIsEmpty = false;
       // assertThat(Boolean.valueOf(email.isEmpty()), equalTo(emailIsEmpty));


    }
}
