package pastebintest.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pastebintest.driver.DriverSingleton;
import pastebintest.pages.StartPage;

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

    @AfterTest(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void pasteCreated() {
       startPage
               .writeCodeInInputField()
               .selectExpiry()
               .enterPasteName()
               .submitPaste();

                //.writeInInputField();

    }

}
