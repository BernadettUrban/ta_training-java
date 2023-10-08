package googlecalculatortest.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class InboxPageTest extends CommonConditions {


    @BeforeClass()
    public void setUp() throws IOException, UnsupportedFlavorException {
        initInboxPageTest();
    }


    @Test
    public void estimateEmailArrivedTest() {

        String mailCount = inboxPage.mailCount();
        assertThat(Boolean.valueOf(mailCount.startsWith("0")), equalTo(false));

    }

    @Test
    public void estimateFromEmailIsCorrect() {

        estimateMailPage = inboxPage.getEmails();
        String emailContent = estimateMailPage.getEstimateFromEmail();

        String expected = "1,081.20";

        assertThat(Boolean.valueOf(emailContent.contains(expected)), equalTo(true));
    }

    @AfterClass
    public void deleteCookies(){
        driver.manage().deleteAllCookies();
    }
}
