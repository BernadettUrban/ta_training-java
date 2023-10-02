package pastebintest.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pastebintest.driver.DriverSingleton;
import pastebintest.pages.ResultPage;
import pastebintest.pages.StartPage;
import pastebintest.util.StringUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static pastebintest.util.StringUtils.*;

public class StartPageTest {

    public final StringUtils stringUtils = new StringUtils();
    protected WebDriver driver;
    private StartPage startPage;
    private ResultPage resultPage;

    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        startPage = new StartPage(driver);
        startPage
                .openPage()
                .clickAgreeButton()
                .closePopups()
                .writeCodeInInputField(CODE)
                .toggleSyntaxSwitch()
                .selectSyntax(SYNTAX)
                .selectExpiry()
                .enterPasteName(TITLE)
                .submitPaste();

        resultPage = startPage
                .savePaste();
    }

    @AfterTest(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void pasteCreatedWithGivenTitle() {
        String name = resultPage.getTitle();
        assertThat(name, equalTo(TITLE));
    }

    @Test
    public void pasteCreatedWithGivenSyntax() {
        String syntax = resultPage.getSyntax();
        assertThat(syntax, equalTo(SYNTAX));
    }

    @Test
    public void pasteCreatedWithGivenCode() {
        String code = resultPage.getCode();
        assertThat(code, equalTo(CODE));
    }

}
