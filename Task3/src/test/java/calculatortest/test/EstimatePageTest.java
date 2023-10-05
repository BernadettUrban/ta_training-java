package calculatortest.test;

import calculatortest.emailpages.EmailGeneratorPage;
import calculatortest.emailpages.MainEmailPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.BeforeTest;

public class EstimatePageTest extends CalculatorPageTest{

    MainEmailPage mainEmailPage;
    EmailGeneratorPage emailGeneratorPage;
    @BeforeTest
    public void setUp(){
        //String originalWindowEstimate = driver.getWindowHandle();
        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get(stringUtils.BASE_URL_FOR_EMAIL);
        String tabForEmailGenerator = newTab.getWindowHandle();
        mainEmailPage = new MainEmailPage(driver);
        emailGeneratorPage = mainEmailPage.clickGenerateEmail();
    }
}
