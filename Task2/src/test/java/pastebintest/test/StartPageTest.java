package pastebintest.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pastebintest.driver.DriverSingleton;
import pastebintest.pages.StartPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StartPageTest {
    protected WebDriver driver;
    private StartPage startPage;

    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        startPage = new StartPage(driver);
        startPage
                .openPage()
                .clickAgreeButton();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void pasteCreated() {

        boolean isCreated = startPage
                                    .writeCodeInInputField()
                                    .toogleSyntaxSwitch()
                                    .selectSyntax()
                                    .selectExpiry()
                                    .enterPasteName()
                                    .submitPaste();

        assertThat(isCreated, equalTo(true));
    }

}
