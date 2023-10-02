package pastebintest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import pastebintest.util.StringUtils;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StartPage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='postform-name']")
    private WebElement nameField;
    @FindBy(xpath = "//*[@id='w0']/div[5]/div[1]/div[10]/button")
    private List<WebElement> submitButton;
    @FindBy(xpath = "//div[@class = 'toggle__control']")
    private WebElement toggleControl;
    @FindBy(xpath = "//*[@class = 'textarea -form js-paste-code']")
    private WebElement pasteTextArea;

    @FindBy(xpath = "//span[@id = 'select2-postform-format-container']")
    private WebElement syntaxHighLightDropdown;

    private JavascriptExecutor js;

    public StartPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public StartPage openPage() {
        driver.get(StringUtils.BASE_URL);
        return this;
    }

    public StartPage clickAgreeButton() {

        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(5000L));
        wait.pollingEvery(Duration.ofMillis(100L));

        List<WebElement> agreeButton = driver.findElements(By.cssSelector(".css-47sehv"));
        agreeButton.get(0).click();
        return this;
    }

    public StartPage closePopups() {
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(4000L));
        wait.pollingEvery(Duration.ofMillis(100L));
        WebElement closeCookie = driver.findElement(By.xpath("//span[@class = 'cookie-button js-close-cookies']"));
        closeCookie.click();
        wait.withTimeout(Duration.ofSeconds(4000L));
        wait.pollingEvery(Duration.ofMillis(100L));

        WebElement closeOtherCookie = driver.findElement(By.xpath("//div[@title = 'Close Me']"));
        closeOtherCookie.click();
        return this;
    }

    public StartPage toggleSyntaxSwitch() {

        toggleControl.click();
        return this;
    }

    public StartPage selectSyntax(String syntax) {
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(3000L));
        wait.pollingEvery(Duration.ofMillis(100L));
        WebElement bannerElement = driver.findElement(By.cssSelector("#hideSlideBanner > svg > path"));
        bannerElement.click();

        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,450)", "");
        WebElement syntaxHighLightDropdown = driver.findElement(By.xpath("//span[@id = 'select2-postform-format-container']"));
        syntaxHighLightDropdown.click();

        WebElement inputSyntaxField = driver.findElement(By.xpath("// input [@class='select2-search__field']"));
        new Actions(driver).moveToElement(inputSyntaxField).click();
        inputSyntaxField.sendKeys(syntax);
        inputSyntaxField.sendKeys(Keys.ENTER);
        return this;
    }

    public StartPage writeCodeInInputField(String code) {
        pasteTextArea.sendKeys(code);
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

    public StartPage enterPasteName(String title) {
        nameField.sendKeys(title);
        return this;

    }

    public StartPage submitPaste() {
        submitButton.get(0).click();
        return this;

    }


    public ResultPage savePaste() {
        return new ResultPage(driver);
    }


}
