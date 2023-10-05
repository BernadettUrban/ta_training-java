package calculatortest.googlepricecalculatorpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalculatorPage extends AbstractPage {

    public static final String BASE_URL = "https://cloud.google.com/products/calculator";
    private final JavascriptExecutor js;
    @FindBy(xpath = "//*[@id='tab-item-1']/div")
    private WebElement computeEngine;
    @FindBy(name = "quantity")
    private WebElement quantity;
    @FindBy(id = "select_value_label_90")
    private WebElement opSystem;
    @FindBy(id = "select_value_label_91")
    private WebElement provisioningModel;
    @FindBy(id = "select_value_label_92")
    private WebElement machineFamily;
    @FindBy(css = "#select_value_label_93")
    //id = "select_value_label_93")
    private WebElement series;
    @FindBy(id = "select_value_label_94")
    private WebElement machineType;
    @FindBy(css = "#mainForm > div:nth-child(3) > div > md-card > md-card-content > div > div:nth-child(1) > form > div:nth-child(15) > div.layout-column.flex-gt-sm-90.flex-80 > md-input-container > md-checkbox")
    private WebElement addGPUs;
    @FindBy(css = "#select_value_label_463")
    private WebElement localSSD;

    @FindBy(xpath = "//*[@id='select_131']")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//*[@id='select_138']")
    private WebElement committedUsage;
    @FindBy(xpath = "//*[@id='mainForm']/div[2]/div/md-card/md-card-content/div/div[1]/form/div[20]/button")
    private WebElement addToEstimateButton;

    public CalculatorPage(WebDriver driver) {
        super(driver);
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CalculatorPage openPage() {
        return this;
    }

    public CalculatorPage switchToMyFrame() {
        driver.manage().timeouts().implicitlyWait(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        final List<WebElement> iframes = driver.findElements(By.cssSelector("iframe"));
        String name = iframes.get(0).getAttribute("name");
        driver.switchTo().frame(name);

        driver.switchTo().frame("myFrame");
        return this;

    }

    public Estimate addSpecifications(String numberOfInstances) {
        computeEngine.click();
        quantity.sendKeys(numberOfInstances);

        js.executeScript("window.scrollBy(0,450)", "");
        series.click();

        WebElement seriesOption = driver.findElement(By.cssSelector("#select_option_220"));
        seriesOption.click();

        machineType.click();
        WebElement machine = driver.findElement(By.xpath(
                "//*[@id='select_container_126']/md-select-menu/md-content/md-optgroup[3]/md-option[4]"));
        waitForClickability(machine, Duration.ofSeconds(10L));
        machine.click();

        addGPUs.click();
        WebElement GPUTypeDropdown = driver.findElement(By.cssSelector("#select_505"));
        waitForClickability(GPUTypeDropdown, Duration.ofSeconds(10L));
        GPUTypeDropdown.click();

        WebElement GPUoption = driver.findElement(By.cssSelector("#select_option_512 > div"));
        GPUoption.click();

        WebElement numberOfGPUsDropdown = driver.findElement(By.cssSelector("#select_value_label_504"));
        numberOfGPUsDropdown.click();

        WebElement valueOfGPUs = driver.findElement(By.cssSelector("#select_option_515"));
        waitForClickability(valueOfGPUs, Duration.ofSeconds(10));
        valueOfGPUs.click();


        waitForClickability(localSSD, Duration.ofSeconds(10L));
        localSSD.click();

        WebElement SSDoption = driver.findElement(By.cssSelector("#select_option_490"));

        waitForClickability(SSDoption, Duration.ofSeconds(10L));
        SSDoption.click();

        datacenterLocation.click();

        WebElement datacenterOption = driver.findElement(By.xpath("//*[@id='select_option_263']"));
        waitForClickability(datacenterOption, Duration.ofSeconds(10L));
        datacenterOption.click();

        committedUsage.click();

        WebElement usageOption = driver.findElement(By.cssSelector("#select_option_136"));

        waitForClickability(usageOption, Duration.ofSeconds(10L));
        usageOption.click();

        addToEstimateButton.click();

        return new Estimate(driver, this);
    }

}
