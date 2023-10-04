package calculatortest.googlepricecalculatorpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalculatorPage extends AbstractPage {

    public static final String BASE_URL = "https://cloud.google.com/products/calculator";
    @FindBy(xpath = "//*[@id='tab-item-1']/div")
    WebElement computeEngine;
    @FindBy(name = "quantity")
    WebElement quantity;
    @FindBy(id = "select_value_label_90")
    WebElement opSystem;
    @FindBy(id = "select_value_label_91")
    WebElement provisioningModel;
    @FindBy(id = "select_value_label_92")
    WebElement machineFamily;
    @FindBy(id = "select_value_label_93")
    WebElement series;
    @FindBy(id = "select_value_label_94")
    WebElement machineType;
    @FindBy(css = "#mainForm > div:nth-child(3) > div > md-card > md-card-content > div > div:nth-child(1) > form > div:nth-child(15) > div.layout-column.flex-gt-sm-90.flex-80 > md-input-container > md-checkbox")
    WebElement addGPUs;


    //#mainForm > div:nth-child(3) > div > md-card > md-card-content > div > div:nth-child(1) > form > div:nth-child(15) > div.layout-column.flex-gt-sm-90.flex-80 > md-input-container > md-checkbox
    public CalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CalculatorPage openPage() {
        driver.navigate().to(BASE_URL);
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        return this;
    }

    public CalculatorPage clickOkButton() {
        WebElement okButton = driver.findElement(By.xpath("/html/body/devsite-snackbar/div/div/button"));
        waitForClickablility(okButton, Duration.ofSeconds(5));
        okButton.click();
        return this;
    }

    public CalculatorPage getIframe() {
        final List<WebElement> iframes = driver.findElements(By.cssSelector("iframe"));
        String name = iframes.get(0).getAttribute("name");
        driver.switchTo().frame(name);

        driver.switchTo().frame("myFrame");
        return this;

    }

    public WebElement waitForClickablility(WebElement element, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Estimate addSpecifications(String numberOfInstances) {
        quantity.sendKeys(numberOfInstances);
        series.click();
        WebElement seriesOption = driver.findElement(By.cssSelector("#select_option_220"));
        waitForClickablility(seriesOption, Duration.ofSeconds(10L));
        //waitForClickablility(seriesOption, Duration.ofSeconds(10));
        //new Actions(driver).moveToElement(seriesOption).click().perform();
        seriesOption.click();
        machineType.click();
        WebElement machine = driver.findElement(By.id("select_option_469"));
        machine.click();

        addGPUs.click();
        WebElement GPUTypeDropdown = driver.findElement(By.cssSelector("#select_505"));
        GPUTypeDropdown.click();

        WebElement tesla = driver.findElement(By.cssSelector("#select_option_512 > div"));
        tesla.click();

        WebElement numberOfGPUsDropdown = driver.findElement(By.cssSelector("#select_value_label_504"));
        numberOfGPUsDropdown.click();

        WebElement valueOfGPUs = driver.findElement(By.cssSelector("#select_option_515"));
        waitForClickablility(valueOfGPUs, Duration.ofSeconds(10));
        valueOfGPUs.click();


        WebElement localSSD = driver.findElement(By.cssSelector("#select_value_label_463"));

        waitForClickablility(localSSD, Duration.ofSeconds(10L));
        localSSD.click();

        WebElement SSDoption = driver.findElement(By.cssSelector("#select_option_490"));

        waitForClickablility(SSDoption, Duration.ofSeconds(10L));
        SSDoption.click();

        WebElement datacenterLocation = driver.findElement(By.xpath("//*[@id='select_131']"));
        datacenterLocation.click();

        WebElement datacenterOption = driver.findElement(By.xpath("//*[@id='select_option_263']"));

        waitForClickablility(datacenterOption, Duration.ofSeconds(10L));
        datacenterOption.click();
        WebElement committedUsage = driver.findElement(By.xpath("//*[@id='select_138']"));
        committedUsage.click();

        WebElement usageOption = driver.findElement(By.cssSelector("#select_option_136"));

        waitForClickablility(usageOption, Duration.ofSeconds(10L));
        usageOption.click();

        WebElement addToEstimateButton = driver.findElement(By.xpath("//*[@id='mainForm']/div[2]/div/md-card/md-card-content/div/div[1]/form/div[20]/button"));
        addToEstimateButton.click();

        return new Estimate(driver, this);
    }


}
