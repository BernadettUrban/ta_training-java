package calculatortest.googlepricecalculatorpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends AbstractPage {

    @FindBy(css = "#gc-wrapper > main > devsite-content > article > article > div > devsite-catalog > div > div.catalog-main > div.catalog-main-filter-controls > div > input")
    WebElement textInput;
    @FindBy(css = "#___gcse_0 > div > div > div > div.gsc-wrapper > div.gsc-resultsbox-visible > div > div > div.gsc-expansionArea > div:nth-child(1) > div.gs-webResult.gs-result > div.gsc-thumbnail-inside > div > a")
    WebElement link;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
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
