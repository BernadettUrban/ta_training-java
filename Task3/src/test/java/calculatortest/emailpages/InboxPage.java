package calculatortest.emailpages;

import calculatortest.googlepricecalculatorpages.AbstractPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class InboxPage extends AbstractPage {

    String BASE_URL = "https://yopmail.com/wm";
    @FindBy(id = "nbmail")
    private WebElement mailCount;

    @FindBy(id = "refresh")
    private WebElement refreshButton;
    @FindBy(id = "ifmail")
    private WebElement mailFraim;

    public InboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public InboxPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public String mailCount() {
        return mailCount.getText();
    }

    public void waitForNewEmail() {
        new FluentWait<>(refreshButton)
                .withTimeout(Duration.ofSeconds(30L))
                .pollingEvery(Duration.ofSeconds(5L))
                .ignoring(NoSuchElementException.class)
                .until(this::isMailArrived);
    }

    private boolean isMailArrived(WebElement button) {
        button.click();
        return !mailCount().startsWith("0");
    }

    public EstimateMailPage getEstimateEmail() {
        driver.switchTo().frame(mailFraim);
        return new EstimateMailPage(driver);
    }


}
