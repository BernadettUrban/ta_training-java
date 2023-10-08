package googlecalculatortest.test;

import googlecalculatortest.driver.DriverSingleton;
import googlecalculatortest.pages.emailpages.EmailGeneratorPage;
import googlecalculatortest.pages.emailpages.EstimateMailPage;
import googlecalculatortest.pages.emailpages.InboxPage;
import googlecalculatortest.pages.emailpages.MainEmailPage;
import googlecalculatortest.pages.googlepricingcalculatorpages.CalculatorPage;
import googlecalculatortest.pages.googlepricingcalculatorpages.EstimatePage;
import googlecalculatortest.pages.googlepricingcalculatorpages.SearchResultPage;
import googlecalculatortest.pages.googlepricingcalculatorpages.StartPage;
import googlecalculatortest.util.StringUtils;
import googlecalculatortest.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

@Listeners({TestListener.class})
public class CommonTestConditions {
    protected WebDriver driver;
    protected StringUtils stringUtils = new StringUtils();
    protected StartPage startPage;
    protected SearchResultPage searchResultPage;
    protected CalculatorPage calculatorPage;
    protected EstimatePage estimate;
    protected EmailGeneratorPage emailGeneratorPage;
    protected MainEmailPage mainEmailPage;
    protected InboxPage inboxPage;
    protected EstimateMailPage estimateMailPage;

    public void initDriver() {
        driver = DriverSingleton.getDriver();

    }

    public void initStartPageTest() {
        initDriver();
        startPage = new StartPage(driver);
        startPage.openPage();
        startPage.clickOkButton();
    }

    public void initSearchResultTest() {
        initStartPageTest();
        searchResultPage = startPage.performSearch(stringUtils.SEARCH_TERM);
    }

    public void initCalculatorPageTest() {
        initSearchResultTest();
        calculatorPage = searchResultPage.navigateToCalculator();

        estimate = calculatorPage.switchToMyFrame()
                .addSpecifications(stringUtils.NUMBER_OF_INSTANCES);
    }

    public void initInboxPageTest() throws IOException, UnsupportedFlavorException {
        initCalculatorPageTest();

        String originalWindowEstimate = driver.getWindowHandle();

        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(stringUtils.BASE_URL_FOR_EMAIL);
        String tabForEmailGenerator = newTab.getWindowHandle();
        mainEmailPage = new MainEmailPage(driver);
        emailGeneratorPage = mainEmailPage.clickGenerateEmail();

        emailGeneratorPage.copyEmailToClipBoard();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String email = (String) clipboard.getData(DataFlavor.stringFlavor);

        driver.switchTo().window(originalWindowEstimate);
        calculatorPage.switchToMyFrame();
        estimate.sendEstimateInEmail(email);
        driver.switchTo().window(tabForEmailGenerator);

        emailGeneratorPage.checkInbox();
        inboxPage = new InboxPage(driver);
        inboxPage.waitForNewEmail();

    }

    @AfterTest(alwaysRun = true)
    public void stopBrowser() {
        driver.manage().deleteAllCookies(); // Deletes all the cookies
        DriverSingleton.closeDriver();
    }
}
