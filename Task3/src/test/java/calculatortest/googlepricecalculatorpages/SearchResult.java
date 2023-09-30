package calculatortest.googlepricecalculatorpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResult extends AbstractPage{


    @FindBy(css = "#___gcse_0 > div > div > div > div.gsc-wrapper > div.gsc-resultsbox-visible > div > div > div.gsc-expansionArea > div:nth-child(1) > div.gs-webResult.gs-result > div.gsc-thumbnail-inside > div > a")
    WebElement link;
    public SearchResult(WebDriver driver) {
        super(driver);
    }

    @Override
    public SearchResult openPage() {
        return null;
    }

    public String clickLink(){
        link.click();
        String url = driver.getCurrentUrl();
        return url;

    }
}
