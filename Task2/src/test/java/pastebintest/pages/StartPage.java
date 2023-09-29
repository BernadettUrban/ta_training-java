package pastebintest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StartPage {
    private static final String BASE_URL = "https://pastebin.com/";
    private static final String CODE = "  git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force\n";
    private static final String TITLE = "how to gain dominance among developers";
    private final WebDriver driver;
    @FindBy(xpath = "//*[@id='postform-name']")
    WebElement nameField;
    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement pasteTextArea;

    @FindBy(xpath = "//*[@id='w0']/div[5]/div[1]/div[10]/button")
    List<WebElement> submitButton;

    public StartPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }

    public StartPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public StartPage clickAgreeButton() {

        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(4000));
        wait.pollingEvery(Duration.ofMillis(100));
        List<WebElement> agreeButton = driver.findElements(By.cssSelector(".css-47sehv"));
        agreeButton.get(0).click();
        return this;
    }

    public StartPage writeCodeInInputField() {
        pasteTextArea.sendKeys(CODE);
        return this;
    }


    public StartPage selectExpiry() {

        WebElement expiryContainer = driver.findElement(By.id("select2-postform-expiration-container"));
        expiryContainer.click();
        Actions actions = new Actions(driver);
        WebElement option = driver.findElement(By.xpath("//ul[contains(@class, 'select2-results__options')]/li[3]"));
        actions.moveToElement(option).click().perform();
        return this;
    }

    public StartPage enterPasteName() {
        nameField.sendKeys(TITLE);
        return this;

    }

    public void submitPaste(){

        submitButton.get(0).click();
    }

}
