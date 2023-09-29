package pastebintest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StartPage {
    private static final String BASE_URL = "https://pastebin.com/";
    private static final String CODE = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force\n";
    private static final String TITLE = "how to gain dominance among developers";
    private final WebDriver driver;

    JavascriptExecutor js;
    @FindBy(xpath = "//*[@id='postform-name']")
    WebElement nameField;
    @FindBy(xpath = "//*[@id='w0']/div[5]/div[1]/div[10]/button")
    List<WebElement> submitButton;
    @FindBy(xpath = "//input[@class = 'js-live-code-highlight']")
    WebElement toggle;
    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement pasteTextArea;

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

    public StartPage toogleSyntaxSwitch() {
        WebElement closeCookie = driver.findElement(By.xpath("//span[@class = 'cookie-button js-close-cookies']"));
        closeCookie.click();

        WebElement closeOtherCookie = driver.findElement(By.xpath("//div[@title = 'Close Me']"));
        closeOtherCookie.click();
        
        WebElement syntaxHighLightDropdown = driver.findElement(By.xpath("//span[@id = 'select2-postform-format-container']"));
        syntaxHighLightDropdown.click();
        WebElement inputSyntaxField = driver.findElement(By.xpath("// input [@class='select2-search__field']"));

        inputSyntaxField.sendKeys("bash");
        inputSyntaxField.sendKeys(Keys.ENTER);
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

    public void submitPaste() {

        submitButton.get(0).click();
    }

}
