package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.emailpages.EmailGeneratorPage;
import calculatortest.emailpages.InboxPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EmailTest {

    InboxPage inboxPage;

    EmailGeneratorPage emailGeneratorPage;

    protected WebDriver driver;
    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        emailGeneratorPage = new EmailGeneratorPage(driver);
        emailGeneratorPage.openPage();
        emailGeneratorPage.clickAgreeButton();
        //inboxPage = new InboxPage(driver);
        //inboxPage.openPage();

    }

    // @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void createRandomEmail(){
        emailGeneratorPage.copyEmailToClipBoard()
                .checkInbox();
    }
}
