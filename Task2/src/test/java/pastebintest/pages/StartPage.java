package pastebintest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import pastebintest.util.StringUtils;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StartPage {
    public final StringUtils stringUtils = new StringUtils();
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='postform-name']")
    WebElement nameField;
    @FindBy(xpath = "//*[@id='w0']/div[5]/div[1]/div[10]/button")
    List<WebElement> submitButton;
    @FindBy(xpath = "//div[@class = 'toggle__control']")
    WebElement toggleControl;
    @FindBy(xpath = "//*[@class = 'textarea -form js-paste-code']")
    WebElement pasteTextArea;

    public StartPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }

    public StartPage openPage() {
        driver.get(stringUtils.BASE_URL);
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
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(4000));
        wait.pollingEvery(Duration.ofMillis(100));
        WebElement closeCookie = driver.findElement(By.xpath("//span[@class = 'cookie-button js-close-cookies']"));
        closeCookie.click();
        wait.withTimeout(Duration.ofSeconds(4000));
        wait.pollingEvery(Duration.ofMillis(100));

        WebElement closeOtherCookie = driver.findElement(By.xpath("//div[@title = 'Close Me']"));
        closeOtherCookie.click();
        toggleControl.click();
        return this;
    }

    public StartPage selectSyntax() {
        WebElement syntaxHighLightDropdown = driver.findElement
                (By.xpath("//span[@id = 'select2-postform-format-container']"));
        syntaxHighLightDropdown.click();

        WebElement inputSyntaxField = driver.findElement(By.xpath("// input [@class='select2-search__field']"));
        inputSyntaxField.sendKeys(stringUtils.SYNTAX);
        inputSyntaxField.sendKeys(Keys.ENTER);
        return this;
    }

    public StartPage writeCodeInInputField() {
        pasteTextArea.click();
        pasteTextArea.sendKeys(stringUtils.CODE);
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
        nameField.sendKeys(stringUtils.TITLE);
        return this;

    }

    public boolean submitPaste() {
        if (submitButton.get(0).isDisplayed()) {
            submitButton.get(0).click();
            return true;
        }
        return false;
    }

    public String getNewUrl(){
        String url = "";
        if (submitButton.get(0).isDisplayed()) {
            submitButton.get(0).click();
            url = driver.getCurrentUrl();
        }
        return url;
    }

    public ResultPage savePaste(){
        ResultPage resultPage = new ResultPage(driver);
        return resultPage;
    }
}
