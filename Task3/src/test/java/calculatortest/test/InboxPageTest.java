package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.emailpages.EmailGeneratorPage;
import calculatortest.emailpages.EstimateMailPage;
import calculatortest.emailpages.InboxPage;
import calculatortest.emailpages.MainEmailPage;
import calculatortest.googlepricecalculatorpages.CalculatorPage;
import calculatortest.googlepricecalculatorpages.Estimate;
import calculatortest.googlepricecalculatorpages.SearchResultPage;
import calculatortest.googlepricecalculatorpages.StartPage;
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

public class InboxPageTest {
    protected WebDriver driver;
    StartPage startPage;
    SearchResultPage resultPage;
    private CalculatorPage calculatorPage;
    Estimate estimate;

    MainEmailPage mainEmailPage;
    EmailGeneratorPage emailGeneratorPage;
    InboxPage inboxPage;
    StringUtils stringUtils = new StringUtils();
    String tabForEmailGenerator;
    EstimateMailPage estimateMailPage;

    @BeforeTest()
    public void setUp() throws IOException, UnsupportedFlavorException {
        driver = DriverSingleton.getDriver();

        driver = DriverSingleton.getDriver();
        startPage = new StartPage(driver);
        startPage.openPage();
        startPage.clickOkButton();
        resultPage = startPage.performSearch(stringUtils.SEARCH_TERM);
        calculatorPage = resultPage.navigateToCalculator();

        //calculatorPage.clickOkButton();
        estimate =  calculatorPage.switchToMyFrame()
                .addSpecifications(stringUtils.NUMBER_OF_INSTANCES);
        String originalWindow = driver.getWindowHandle();
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(stringUtils.BASE_URL_FOR_EMAIL);
        tabForEmailGenerator = newTab.getWindowHandle();
        mainEmailPage = new MainEmailPage(driver);
        emailGeneratorPage = mainEmailPage.clickGenerateEmail();
                //new EmailGeneratorPage(driver);


       // emailGeneratorPage.clickAgreeButton();
        emailGeneratorPage.copyEmailToClipBoard();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String email = (String) clipboard.getData(DataFlavor.stringFlavor);

        driver.switchTo().window(originalWindow);
        calculatorPage.switchToMyFrame();
        estimate.sendEstimateInEmail(email);
        driver.switchTo().window(tabForEmailGenerator);

        emailGeneratorPage.checkInbox();
        inboxPage = new InboxPage(driver);
        inboxPage.waitForNewEmail();

    }

    // @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void estimateEmailArrivedTest() throws IOException, UnsupportedFlavorException {



        String mailcount = inboxPage.mailCount();
        boolean startsWithZero = mailcount.startsWith("0");
        assertThat(Boolean.valueOf(mailcount.startsWith("0")), equalTo(false));

    }

    @Test
    public void estimateFromEmailIsCorrect(){

        estimateMailPage = inboxPage.getEstimateEmail();
                //new EstimateMailPage(driver);
        String emailContetnt = estimateMailPage.getEstimateFromEmail();
        String expectedMonthylyCost = "1,081.20";
        assertThat(Boolean.valueOf(emailContetnt.contains(expectedMonthylyCost)), equalTo(true));
    }
}
