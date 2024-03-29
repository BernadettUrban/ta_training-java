package calculatortest.googlepricecalculatorpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalculatorPage extends AbstractPage {

    private final JavascriptExecutor js;
    @FindBy(css = "#tab-item-1 > div")
    private WebElement computeEngine;
    @FindBy(name = "quantity")
    private WebElement quantity;
    @FindBy(css = "#select_value_label_90")
    private WebElement opSystem;
    @FindBy(css = "#select_value_label_91")
    private WebElement provisioningModel;
    @FindBy(css = "#select_value_label_92")
    private WebElement machineFamily;
    @FindBy(css = "#select_value_label_93")
    private WebElement series;

    @FindBy(css = "#select_option_220")
    private WebElement seriesOption;

    @FindBy(css = "#select_value_label_94")
    private WebElement machineType;
    @FindBy(
            //css = "#select_option_294")
            xpath = "//*[@id='select_container_126']/md-select-menu/md-content/md-optgroup[3]/md-option[4]")
    private WebElement machine;
    @FindBy(css = "#mainForm > div:nth-child(3) > div > md-card > md-card-content > div > div:nth-child(1) > form > div:nth-child(15) > div.layout-column.flex-gt-sm-90.flex-80 > md-input-container > md-checkbox")
    private WebElement addGPUs;
    @FindBy(css = "#select_505")
    private WebElement GPUTypeDropdown;
    @FindBy(css = "#select_option_512 > div")
    private WebElement GPUoption;

    @FindBy(css = "#select_value_label_504")
    private WebElement numberOfGPUsDropdown;
    @FindBy(css = "#select_option_515")
    private WebElement valueOfGPUs;


    @FindBy(css = "#select_value_label_463")
    private WebElement localSSD;
    @FindBy(css = "#select_option_490")
    private WebElement SSDoption;


    @FindBy(css = "#select_131")
    private WebElement datacenterLocation;
    @FindBy(css = "#select_option_263")
    private WebElement datacenterOption;

    @FindBy(css = "#select_138")
    private WebElement committedUsage;
    @FindBy(css = "#select_option_136")
    private WebElement usageOption;
    @FindBy(css = "#mainForm > div:nth-child(3) > div > md-card > md-card-content > div > div:nth-child(1) > form > div.layout-align-end-start.layout-row > button")
    //xpath = "//*[@id='mainForm']/div[2]/div/md-card/md-card-content/div/div[1]/form/div[20]/button")
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

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", seriesOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", machineType);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", machine);


        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addGPUs);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", GPUTypeDropdown);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", GPUoption);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", numberOfGPUsDropdown);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", valueOfGPUs);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", localSSD);


        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", SSDoption);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", datacenterLocation);


        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", datacenterOption);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", committedUsage);

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", usageOption);
        addToEstimateButton.click();

        return new Estimate(driver, this);
    }
}
