package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.googlepricecalculatorpages.SearchResultPage;
import calculatortest.googlepricecalculatorpages.StartPage;
import calculatortest.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StartPageTest {
    protected WebDriver driver;
    private StartPage startPage;
    private StringUtils stringUtils = new StringUtils();
    private SearchResultPage searchResult;
    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        startPage = new StartPage(driver);
        startPage.openPage();
        startPage.clickOkButton();
    }

    //@AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void search(){
        searchResult = startPage.performSearch(stringUtils.SEARCH_TERM);
        String actual = searchResult.getTextFromInput();
        assertThat(actual, equalTo(stringUtils.SEARCH_TERM));

    }

}
