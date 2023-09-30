package calculatortest.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends AbstractPage{

    public static final String BASE_URL = "https://cloud.google.com/";
    public static final String QUERY = "Google Cloud Platform Pricing Calculator";

    @FindBy(name = "q")
    WebElement searchBar;

    public StartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public StartPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public StartPage performSearch(){
        searchBar.click();
        searchBar.sendKeys(QUERY);
        searchBar.sendKeys(Keys.ENTER);
        return this;
    }

    public String getUrl(){
        String url = driver.getCurrentUrl();
        return url;
    }

    public SearchResult goToSearchResult(){
        SearchResult searchResult = new SearchResult(driver);
        return searchResult;
    }


}
