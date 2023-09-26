package packageName.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import packageName.driver.DriverSingleton;
import packageName.pages.StartPage;

import static com.google.common.base.CharMatcher.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StartPageTest {

    protected WebDriver driver;
    public static final String CODE = "Hello from WebDriver";

    @BeforeMethod()
    public void setUp()
    {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser()
    {
        DriverSingleton.closeDriver();
    }

    @Test
    public void userCanWriteCode(){
    String codeInput = new StartPage(driver)
            .openPage()
            .clickAgreeButton()
            .writeInInputField();


        assertThat(codeInput, equalTo(CODE));
    }

    @Test
    public void userCanSelectExpiry(){
        String expiry = new StartPage(driver)
                .openPage()
                .clickAgreeButton()
                .selectExpiry();
        assertThat(expiry, equalTo("10 Minutes"));
    }

    @Test
    public void userCanNamePasteBin(){
        boolean filled = new StartPage(driver)
                .openPage()
                .clickAgreeButton()
                .enterPasteName();
        assertThat(filled, equalTo(true));
    }
}
