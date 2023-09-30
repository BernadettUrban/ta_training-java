package calculatortest.test;

import calculatortest.driver.DriverSingleton;
import calculatortest.googlepricecalculatorpages.SearchResult;
import calculatortest.googlepricecalculatorpages.StartPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchResultTest {
    protected WebDriver driver;
    private SearchResult searchResult;
    StartPage startPage;

    @BeforeTest()
    public void setUp() {
        driver = DriverSingleton.getDriver();

        startPage = new StartPage(driver);
        searchResult = startPage.goToSearchResult();
        driver.get(startPage.getUrl());

    }

   // @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }

    @Test
    public void something(){
        searchResult.clickLink();
        System.out.println(driver.getCurrentUrl());
    }
}
