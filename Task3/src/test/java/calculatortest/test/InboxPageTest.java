package calculatortest.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class InboxPageTest extends CommonTestConditions {


    @BeforeTest()
    public void setUp() throws IOException, UnsupportedFlavorException {
        initInboxPageTest();
    }


    @Test
    public void estimateEmailArrivedTest() throws IOException, UnsupportedFlavorException {

        String mailCount = inboxPage.mailCount();
        assertThat(Boolean.valueOf(mailCount.startsWith("0")), equalTo(false));

    }

    @Test
    public void estimateFromEmailIsCorrect() {

        estimateMailPage = inboxPage.getEstimateEmail();
        String emailContent = estimateMailPage.getEstimateFromEmail();

        String expected = "1,081.20";

        assertThat(Boolean.valueOf(emailContent.contains(expected)), equalTo(true));
    }
}
