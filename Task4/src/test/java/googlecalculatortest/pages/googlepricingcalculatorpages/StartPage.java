package googlecalculatortest.pages.googlepricingcalculatorpages;

import com.google.common.base.Function;
import googlecalculatortest.pages.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class StartPage extends AbstractPage {
    @FindBy(name = "q")
    private WebElement searchBar;

    public StartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public StartPage openPage() {
        driver.navigate().to(stringUtils.BASE_URL_FOR_GOOGLE);
        return this;
    }

    public StartPage clickOkButton() {
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(5000L));
        wait.pollingEvery(Duration.ofMillis(100L));
        WebElement okButton = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver input) {
                return driver.findElement(By.cssSelector("#yDmH0d > div.U0ljZb > div > div > button"));
            }

        });
        okButton.click();
        return this;
    }

    public SearchResultPage performSearch(String term) {
        searchBar.click();
        searchBar.sendKeys(term);
        searchBar.sendKeys(Keys.ENTER);
        return new SearchResultPage(driver);
    }


}
