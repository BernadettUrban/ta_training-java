package calculatortest.emailpages;

import calculatortest.googlepricecalculatorpages.AbstractPage;
import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage {


    public static final String BASE_URL ="https://yopmail.com/";

    protected MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected MainPage openPage() {
        return null;
    }

    public MainPage clickAgreeButton(){
        ////*[@id="accept"]
        return this;
    }
}
