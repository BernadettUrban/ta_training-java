package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.googlepricecalculatorpages.SearchResultPage;
import calculatortest.googlepricecalculatorpages.StartPage;
import calculatortest.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchResultPageTest {
    protected WebDriver driver;
    private SearchResultPage searchResult;
    StartPage startPage;
    StringUtils stringUtils = new StringUtils();
    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        startPage = new StartPage(driver);
        startPage.openPage();
        startPage.clickOkButton();
        searchResult = startPage.performSearch(stringUtils.SEARCH_TERM);

    }

   // @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void clickOnFirstLink(){
        String url = searchResult.clickLink();
        String expected = "https://cloud.google.com/products/calculator";
        assertThat(url, equalTo(expected));
    }


}
