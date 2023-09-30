package calculatortest.emailpages;

import calculatortest.googlepricecalculatorpages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class InboxPage extends AbstractPage {

    String BASE_URL = "https://yopmail.com/wm";
    public InboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public InboxPage openPage() {
        driver.get(BASE_URL);
        return this;
    }
}
