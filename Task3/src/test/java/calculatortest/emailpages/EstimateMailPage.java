package calculatortest.emailpages;

import calculatortest.googlepricecalculatorpages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EstimateMailPage extends AbstractPage {

    @FindBy(css = "#mail h2")
    private WebElement monthlyCost;

    public EstimateMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

    public String getEstimateFromEmail() {

        return monthlyCost.getText();
    }
}
