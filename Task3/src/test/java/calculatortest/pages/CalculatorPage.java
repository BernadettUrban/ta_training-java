package calculatortest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalculatorPage extends AbstractPage{

    public static final String BASE_URL ="https://cloud.google.com/products/calculator";
    private int noOfInstances = 4;

   
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

    public CalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CalculatorPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public CalculatorPage clickOkButton(){
        WebElement okButton = driver.findElement(By.xpath("/html/body/devsite-snackbar/div/div/button"));
        okButton.click();
        return this;
    }
    public void getIframe() {
        final List<WebElement> iframes = driver.findElements(By.cssSelector("iframe"));
        String name = iframes.get(0).getAttribute("name");
        driver.switchTo().frame(name);

        driver.switchTo().frame("myFrame");

    }
    public void addSpecifications(){
        quantity.sendKeys("4");
        series.click();
        WebElement option = driver.findElement(By.id("select_option_220"));
        option.click();
        machineType.click();
        WebElement machine = driver.findElement(By.id("select_option_469"));
        machine.click();



        /*
        WebElement addGPU = driver.findElement(By.xpath("//*[@aria-label='Add GPUs']"));
        addGPU.click();


            WebElement GPUTypeDropdown = driver.findElement(
                    //By.cssSelector("#select_value_label_503"));
                    By.xpath("//*[@placeholder='GPU type']"));
            GPUTypeDropdown.click();

            /*
            <md-option ng-value="item.value" ng-disabled="listingCtrl.checkGpuAvailability(item.value, 'computeServer')"
            ng-repeat="item in listingCtrl.gpuList" tabindex="0" class="ng-scope md-ink-ripple" role="option"
            aria-selected="true" id="select_option_553" aria-checked="true" aria-disabled="false" value="NVIDIA_TESLA_V100"
            selected="selected"><div class="md-text ng-binding">
                                NVIDIA Tesla V100
                            </div></md-option>
             */
          /*  WebElement tesla = driver.findElement(By.xpath("md-option[value = 'NVIDIA_TESLA_V100']"));
            tesla.click();

            WebElement numberOfGPUs = driver.findElement(By.xpath("//*[@placeholder='Number of GPUs']"));
            numberOfGPUs.click();

            WebElement value = driver.findElement(By.id("select_value_label_504"));
            value.click();

           */

        WebElement localSSD = driver.findElement(By.xpath("//*[@id='select_464']"));

        localSSD.click();
        WebElement SSDoption = driver.findElement(By.xpath("//*[@id='select_option_490']"));

        SSDoption.click();

        WebElement datacenterLocation = driver.findElement(By.xpath("//*[@id='select_131']"));

        WebElement datacenterOption = driver.findElement(By.xpath("//*[@id='select_option_263']"));

        WebElement committedUsage = driver.findElement(By.xpath("//*[@id='select_138']"));
        WebElement usageOption = driver.findElement(By.xpath("//*[@id='select_value_label_97']"));

    }

}
