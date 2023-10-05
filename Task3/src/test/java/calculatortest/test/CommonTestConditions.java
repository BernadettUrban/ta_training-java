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
import org.testng.annotations.AfterTest;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CommonTestConditions {
    public String expectedMonthlyCost;
    protected WebDriver driver;
    protected StringUtils stringUtils = new StringUtils();
    protected StartPage startPage;
    protected SearchResultPage searchResultPage;
    protected CalculatorPage calculatorPage;
    protected Estimate estimate;
    protected EmailGeneratorPage emailGeneratorPage;
    MainEmailPage mainEmailPage;
    InboxPage inboxPage;
    EstimateMailPage estimateMailPage;

    public void initDriver() {
        driver = DriverSingleton.getDriver();

    }
    
    public void initStartPage() {
        initDriver();
        startPage = new StartPage(driver);
        startPage.openPage();
        startPage.clickOkButton();
    }

    public void initSearchResult() {
        initStartPage();
        searchResultPage = startPage.performSearch(stringUtils.SEARCH_TERM);
    }

    public void initCalculatorPageTest() {
        initSearchResult();
        calculatorPage = searchResultPage.navigateToCalculator();

        estimate = calculatorPage.switchToMyFrame()
                .addSpecifications(stringUtils.NUMBER_OF_INSTANCES);
    }

    public String getMonthlyCostFromEstimate() {
        return stringUtils.regexForUSD(estimate.totalEstimatedCost());
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
        DriverSingleton.closeDriver();
    }
}
