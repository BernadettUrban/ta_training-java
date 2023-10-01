package calculatortest.googlepricecalculatorpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.PageFactory.initElements;

public class Estimate extends AbstractPage{
    private final CalculatorPage calculator;
    @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-card-content/div/div/div/div[1]")
            //css = "div.cpc-cart-total > h2.md-title > b.ng-binding")
    ////*[@id="resultBlock"]/md-card/md-card-content/div/div/div/div[1]
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

    public Estimate sendEstimateInEmail(String email){
        WebElement emailField = driver.findElement(By.xpath("//*[@id='input_615']"));
        emailField.click();
        emailField.sendKeys(email);
        WebElement sendEmailButton = driver.findElement(By.xpath("//*[@id='dialogContent_621']/form/md-dialog-actions/button[2]"));
        sendEmailButton.click();
        return this;
    }

}
