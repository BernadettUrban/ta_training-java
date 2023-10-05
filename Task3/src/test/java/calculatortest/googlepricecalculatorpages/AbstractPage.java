package calculatortest.googlepricecalculatorpages;

import calculatortest.util.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected WebDriver driver;
    protected StringUtils stringUtils = new StringUtils();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;

    }

    protected abstract AbstractPage openPage();

    public WebElement waitForClickability(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}