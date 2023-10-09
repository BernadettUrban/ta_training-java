package googlecalculatortest.pages.googlepricingcalculatorpages;

import googlecalculatortest.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPage extends AbstractPage {

    @FindBy(css = ".catalog-filter-bar")
    private WebElement textInput;
    @FindBy(css =
            "div.gsc-result:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1)")

    private WebElement link;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchResultPage openPage() {
        return this;
    }


    public String getTextFromInput() {
        return textInput.getAttribute("value");
    }

    public String clickLink() {
        link.click();
        String url = driver.getCurrentUrl();
        return url;

    }

    public CalculatorPage navigateToCalculator() {
        link.click();
        return new CalculatorPage(driver);
    }
}
