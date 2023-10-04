package calculatortest.googlepricecalculatorpages;

import calculatortest.util.StringUtils;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class AbstractPage
{
    protected WebDriver driver;

    protected abstract AbstractPage openPage();
    protected final int WAIT_TIMEOUT_SECONDS = 30;
    protected StringUtils stringUtils = new StringUtils();

    protected AbstractPage(WebDriver driver)
    {
        this.driver = driver;

    }
}