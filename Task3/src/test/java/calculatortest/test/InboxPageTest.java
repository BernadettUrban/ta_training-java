package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.emailpages.EmailGeneratorPage;
import calculatortest.emailpages.InboxPage;
import calculatortest.googlepricecalculatorpages.CalculatorPage;
import calculatortest.googlepricecalculatorpages.Estimate;
import calculatortest.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
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
    private CalculatorPage calculatorPage;
    Estimate estimate;
    EmailGeneratorPage emailGeneratorPage;
    InboxPage inboxPage;
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
    public void estimeteEmailArrived() throws IOException, UnsupportedFlavorException {

        emailGeneratorPage = new EmailGeneratorPage(driver);
        String originalWindow = driver.getWindowHandle();
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(stringUtils.BASE_URL_FOR_EMAIL_GENERATOR);
        String tabForEmailGenerator = newTab.getWindowHandle();

        emailGeneratorPage.clickAgreeButton();
        emailGeneratorPage.copyEmailToClipBoard();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        String email = (String) clipboard.getData(DataFlavor.stringFlavor);

        driver.switchTo().window(originalWindow);
        calculatorPage.getIframe();
        estimate.sendEstimateInEmail(email);

        driver.switchTo().window(tabForEmailGenerator);

        emailGeneratorPage.checkInbox();
        inboxPage = new InboxPage(driver);
        inboxPage.waitForNewEmail();

        String mailcount = inboxPage.mailCount();
        boolean startsWithZero = mailcount.startsWith("0");
        assertThat(Boolean.valueOf(mailcount.startsWith("0")), equalTo(false));

    }
}
