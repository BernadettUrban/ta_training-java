package googlecalculatortest.pages;


import googlecalculatortest.util.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    public WebDriver driver;
    protected StringUtils stringUtils = new StringUtils();

    protected final JavascriptExecutor js;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(this.driver, this);
    }

    protected abstract AbstractPage openPage();

    public WebElement waitForClickability(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void  callJsExecutorToClick(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }
}