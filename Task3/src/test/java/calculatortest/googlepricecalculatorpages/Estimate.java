package calculatortest.googlepricecalculatorpages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.PageFactory.initElements;

public class Estimate extends AbstractPage{
    private final CalculatorPage calculator;
    private JavascriptExecutor js;
    @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-toolbar/div/h2[2]")
    WebElement monthlyEstimate;

    @FindBy(xpath = "//*[@id='compute']/md-list/div/div[1]/div/div/span")
    WebElement numberOfEngines;
    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[1]/div[1]")
    WebElement region;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[3]/div[1]")
    WebElement commitmentTerm;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[4]/div[1]")
    WebElement provisioningModel;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[5]/div[1]")
    WebElement instanceType;

    @FindBy(xpath =
    "//*[@id='compute']/md-list/md-list-item[6]/div[1]")
    WebElement operatingSystem;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[7]/div[1]")
    WebElement localSSD;
    @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-card-content/div/div/div/div[1]")
    private WebElement totalEstimatedCost;

    @FindBy(css = "button.md-button.md-mini[title='Email Estimate']")
    private WebElement emailEstimateButton;


    protected Estimate(WebDriver driver, CalculatorPage calculator) {
        super(driver);
        this.calculator = calculator;
        PageFactory.initElements(calculator.driver, this);
    }

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    public String totalEstimatedCost() {
        return totalEstimatedCost.getText();
    }

    public String getMonthlyEstimate(){
        return monthlyEstimate.getText();
    }

    public String getNumberOfEngines(){
        return numberOfEngines.getText();
    }

    public String getRegion(){
        return  region.getText();
    }

    public String getCommitmentTerm(){
        return commitmentTerm.getText();
    }

    public String getProvisioningModel(){
        return provisioningModel.getText();
    }
    public String getInstanceType(){
        return instanceType.getText();
    }


    public String getOperatingSystem(){
        return  operatingSystem.getText();
    }

    public String getLocalSSD(){
        return localSSD.getText();
    }

    public Estimate clickSendEstimateButton(){
        emailEstimateButton.click();
        return this;
    }



    public Estimate sendEstimateInEmail(String email){
        js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,450)", "");
        emailEstimateButton.click();
        WebElement emailField = driver.findElement(By.xpath("//*[@id='input_615']"));
        emailField.click();
        emailField.sendKeys(email);
        WebElement sendEmailButton = driver.findElement(By.xpath("//*[@id='dialogContent_621']/form/md-dialog-actions/button[2]"));
        sendEmailButton.click();
        return this;
    }

}
