package googlecalculatortest.pages.googlepricingcalculatorpages;

import googlecalculatortest.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EstimatePage extends AbstractPage {
    @FindBy(css = "#compute > md-toolbar > h2 > span:nth-child(1)")
    private WebElement computeEngineSign;
    @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-toolbar/div/h2[2]")
    private WebElement monthlyEstimate;

    @FindBy(xpath = "//*[@id='compute']/md-list/div/div[1]/div/div/span")
    private WebElement numberOfEngines;
    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[1]/div[1]")
    private WebElement region;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[3]/div[1]")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[4]/div[1]")
    private WebElement provisioningModel;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[5]/div[1]")
    private WebElement instanceType;

    @FindBy(xpath =
            "//*[@id='compute']/md-list/md-list-item[6]/div[1]")
    private WebElement operatingSystem;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[7]/div[1]")
    private WebElement localSSD;
    @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-card-content/div/div/div/div[1]")
    private WebElement totalEstimatedCost;

    @FindBy(xpath = "//*[@id='select_462']")
    private WebElement currencyDropdown;

    @FindBy(xpath = "//*[@id='input_615']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='dialogContent_621']/form/md-dialog-actions/button[2]")
    private WebElement sendEmailButton;

    @FindBy(css = "button.md-button.md-mini[title='Email Estimate']")
    private WebElement emailEstimateButton;


    protected EstimatePage(WebDriver driver, CalculatorPage calculator) {
        super(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return this;
    }

    public String getComputeEngineSign() {
        return computeEngineSign.getText();
    }

    public String totalEstimatedCost() {
        return totalEstimatedCost.getText();
    }

    public String getMonthlyEstimate() {
        return monthlyEstimate.getText();
    }

    public String getNumberOfEngines() {
        return numberOfEngines.getText();
    }

    public String getRegion() {
        return region.getText();
    }

    public String getCommitmentTerm() {
        return commitmentTerm.getText();
    }

    public String getProvisioningModel() {
        return provisioningModel.getText();
    }

    public String getInstanceType() {
        return instanceType.getText();
    }


    public String getOperatingSystem() {
        return operatingSystem.getText();
    }

    public String getLocalSSD() {
        return localSSD.getText();
    }


    public EstimatePage sendEstimateInEmail(String email) {

        js.executeScript("window.scrollBy(0,550)", "");
        callJsExecutorToClick(emailEstimateButton);
        callJsExecutorToClick(emailField);
        emailField.sendKeys(email);
        sendEmailButton.click();
        return this;
    }

}
