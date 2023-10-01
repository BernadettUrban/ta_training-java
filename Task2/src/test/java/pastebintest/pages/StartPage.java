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
    private final StringUtils stringUtils = new StringUtils();
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

    @FindBy(xpath = "//ul/li[text()[contains(.,'Bash')]]")
    private WebElement inputSyntaxField;

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

    public StartPage closePopups(){
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(4000));
        wait.pollingEvery(Duration.ofMillis(100));
        WebElement closeCookie = driver.findElement(By.xpath("//span[@class = 'cookie-button js-close-cookies']"));
        closeCookie.click();
        wait.withTimeout(Duration.ofSeconds(4000));
        wait.pollingEvery(Duration.ofMillis(100));

        WebElement closeOtherCookie = driver.findElement(By.xpath("//div[@title = 'Close Me']"));
        closeOtherCookie.click();
        return this;
    }

    public StartPage toogleSyntaxSwitch() {

        toggleControl.click();
        return this;
    }

    public StartPage selectSyntax() {
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(Duration.ofSeconds(4000));
        wait.pollingEvery(Duration.ofMillis(100));
        WebElement bannerElement = driver.findElement(By.cssSelector("#hideSlideBanner > svg > path"));
        bannerElement.click();

        Actions actions = new Actions(driver);
        //syntaxHighLightDropdown.click();
        actions.moveToElement(syntaxHighLightDropdown).click();

      new Actions(driver).moveToElement(inputSyntaxField).click();
       // inputSyntaxField.click();
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

    public StartPage submitPaste() {
        submitButton.get(0).click();
        return this;

    }


    public ResultPage savePaste(){
        return new ResultPage(driver);
    }


}
